package ISA.BloodBank.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.AppointmentDTO;
import ISA.BloodBank.dto.AppointmentRegisteredUserDTO;
import ISA.BloodBank.iservice.IAppointmentService;
import ISA.BloodBank.model.Appointment;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.model.RegisteredUser;
import ISA.BloodBank.repository.IAppointmentRepository;

@Service
@Transactional
public class AppointmentService implements IAppointmentService {

	private static ReentrantLock lock = new ReentrantLock();
	private IAppointmentRepository appointmentRepository;

	private CenterAdministratorService centerAdministaratorService;

	private MedicalCenterService medicalCenterService;

	private UserService userService;
	private EmailService emailService;

	@Autowired
	public AppointmentService(IAppointmentRepository appointmentRepository, @Lazy CenterAdministratorService centerAdministaratorService,
							  UserService userService, MedicalCenterService medicalCenterService, EmailService emailService) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.centerAdministaratorService = centerAdministaratorService;
		this.userService = userService;
		this.medicalCenterService = medicalCenterService;
		this.emailService = emailService;
	}

	@Override
	public Appointment createPredefinedAppointment(AppointmentDTO appointmentDTO) throws InterruptedException {
		if(lock.isLocked())
			throw new InterruptedException();
		lock.lock();
		try {
			Appointment appointment = new Appointment();
			CenterAdministrator centerAdministrator = new CenterAdministrator();
			centerAdministrator = centerAdministaratorService.findById(Long.parseLong(appointmentDTO.getAdministratorCenterID()));
			appointment.setCenterAdministrator(centerAdministrator);
			MedicalCenter medicalCenter = centerAdministrator.getMedicalCenter();
			appointment.setMedicalCenter(medicalCenter);
			appointment.setIsAvailable(true);
			appointment.setIsCancelled(false);
			appointment.setRegisteredUser(null);
			appointment.setDuration(appointmentDTO.getDuration());
			String time = appointmentDTO.getTime();
			String date = appointmentDTO.getDate();
			String dateParts[] = date.split("T");
			String dateAndTime = dateParts[0] + ' ' + time;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
			appointment.setDate(dateTime);
			appointmentRepository.save(appointment);
			Thread.sleep(10000);
			return appointment;
		}catch (Exception ex){
			throw new InterruptedException("Appointment already scheduled!") ;
		} finally {
            lock.unlock();
        }
	}

	@Override
	public Appointment createAppointmentRegisteredUser(AppointmentRegisteredUserDTO appointmentRegisteredUserDTO) {
		Appointment appointment = new Appointment();
		String time = appointmentRegisteredUserDTO.getTime();
		String date = appointmentRegisteredUserDTO.getDate();
		String dateAndTime = date + ' ' + time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
		List<CenterAdministrator> administrators = centerAdministaratorService.GetFreeCenterAdministartior(Long.parseLong(appointmentRegisteredUserDTO.getMedicalCenterID()), date, time);
		MedicalCenter medicalCenter = new MedicalCenter();
		RegisteredUser registeredUser = new RegisteredUser();
		medicalCenter  = medicalCenterService.findById(Long.parseLong(appointmentRegisteredUserDTO.getMedicalCenterID()));
		appointment.setCenterAdministrator(administrators.get(0));
		appointment.setMedicalCenter(medicalCenter);
		appointment.setIsAvailable(false);
		appointment.setIsCancelled(false);
		registeredUser = (RegisteredUser)userService.findById(Long.parseLong(appointmentRegisteredUserDTO.getRegisteredUserID()));
		appointment.setRegisteredUser(registeredUser);
		appointment.setDuration("15");
		appointment.setDate(dateTime);
		appointmentRepository.save(appointment);
		emailService.sendNotificationForScheduledAppointment(registeredUser.getEmail(), appointment);
		return appointment;

	}

	@Override
	public List<Appointment> findAllByCenterId(Long id) {
		return appointmentRepository.findAppointmentsByCenterAdministratorMedicalCenterCenterId(id);
	}

	@Transactional
	public boolean schedulePredefinedAppointment(Long appointmentId, Long registeredUserId) {
		RegisteredUser registeredUser = (RegisteredUser)userService.findById(registeredUserId);
		if(registeredUser == null || registeredUser.getPenalties() >= 3) {
			return false;
		}
		try {
			Appointment schedulingAppointment = appointmentRepository.findOneById(appointmentId);
			if (schedulingAppointment == null || !schedulingAppointment.getIsAvailable()) {
				return false;
			}
			schedulingAppointment.setIsAvailable(false);
			schedulingAppointment.setRegisteredUser(registeredUser);
			appointmentRepository.save(schedulingAppointment);
			emailService.sendNotificationForScheduledAppointment(registeredUser.getEmail(), schedulingAppointment);
			return true;

		} catch(PessimisticLockingFailureException ex) {
			throw new PessimisticLockingFailureException("Appointment already scheduled!");
		}
	}

	@Override
	public List<Appointment> getAll() {
		return appointmentRepository.findAll();
	}

	public List<Appointment> getAllAppointmentsByMedicalCenterIdAndDate(Long id, String date, String time) {
		List<Appointment> medCenterAppointments = appointmentRepository.findAppointmentsByMedicalCenterCenterId(id);
		List<Appointment> appointments = new ArrayList<Appointment>();
		String dateAndTime = date + ' ' + time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
		for(Appointment a : medCenterAppointments) {
			if(a.getDate().isEqual(dateTime)) {
				appointments.add(a);
			} else {continue;}
		}

		return appointments;
	}

	//za test
	public List<Appointment> getAllAvailableAppointmentsByMedicalCenterId(Long id) {
		List<Appointment> medCenterAppointments = appointmentRepository.findAppointmentsByMedicalCenterCenterId(id);
		List<Appointment> appointments = new ArrayList<Appointment>();
		for(Appointment a : medCenterAppointments) {
			if(a.getIsAvailable()) {
				appointments.add(a);
			} else {continue;}
		}
		return appointments;
	}

	public List<Appointment> findAllByRegisteredUserId(Long id) {
		return appointmentRepository.findAppointmentsByRegisteredUserUserId(id);
	}

	@Override
	public Appointment findById(Long appointmentId) {
		return appointmentRepository.findByAppointmentId(appointmentId);
	}

	@Override
	public Appointment cancelScheduledAppointment(Long appointmentId) {
		Appointment cancelingAppointment = appointmentRepository.findByAppointmentId(appointmentId);

		cancelingAppointment.setIsAvailable(true);
		cancelingAppointment.setIsCancelled(true);

		appointmentRepository.save(cancelingAppointment);
		//userService.updatePenal(cancelingAppointment.getRegisteredUser().getUserId());
		return cancelingAppointment;
	}

	@Override
	public List<Appointment> getAllAppointmentsByAdministratorId(Long centerAdministratorId) {
		List<Appointment> listAppointments = new ArrayList<Appointment>();
		listAppointments = getAll();
		List<Appointment> retVal = new ArrayList<Appointment>();
		for(Appointment app: listAppointments) {
			if(app.getCenterAdministrator().getUserId().equals(centerAdministratorId) && app.getRegisteredUser() != null) {
				retVal.add(app);
			}
		}
		return retVal;
	}

}
