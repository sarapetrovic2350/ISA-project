package ISA.BloodBank.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.AppointmentDTO;
import ISA.BloodBank.dto.AppointmentRegisteredUserDTO;
import ISA.BloodBank.iservice.IAppointmentService;
import ISA.BloodBank.model.Appointment;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.RegisteredUser;
import ISA.BloodBank.repository.IAppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService {
	
	private IAppointmentRepository appointmentRepository;
	
	private CenterAdministratorService centerAdministaratorService;
	
	private UserService userService;
	private EmailService emailService;
	
	@Autowired
	public AppointmentService(IAppointmentRepository appointmentRepository, CenterAdministratorService centerAdministaratorService,
			UserService userService, EmailService emailService) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.centerAdministaratorService = centerAdministaratorService;
		this.userService = userService;
		this.emailService = emailService;
	}
	
	@Override
	public Appointment createPredefinedAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = new Appointment();
		CenterAdministrator centerAdministrator = new CenterAdministrator();
		centerAdministrator = centerAdministaratorService.findById(Long.parseLong(appointmentDTO.getAdministratorCenterID()));
		appointment.setCenterAdministrator(centerAdministrator);
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
		
		return appointment;
		
	}
	
	@Override
	public Appointment createAppointmentRegisteredUser(AppointmentRegisteredUserDTO appointmentRegisteredUserDTO) {
		Appointment appointment = new Appointment();
		CenterAdministrator centerAdministrator = new CenterAdministrator();
		RegisteredUser registeredUser = new RegisteredUser();
		centerAdministrator = centerAdministaratorService.findById(Long.parseLong(appointmentRegisteredUserDTO.getAdministratorCenterID()));
		appointment.setCenterAdministrator(centerAdministrator);
		appointment.setIsAvailable(false);
		appointment.setIsCancelled(false);
		registeredUser = (RegisteredUser)userService.findById(Long.parseLong(appointmentRegisteredUserDTO.getRegisteredUserID()));
		appointment.setRegisteredUser(registeredUser);
		appointment.setDuration(appointmentRegisteredUserDTO.getDuration());
		String time = appointmentRegisteredUserDTO.getTime();
		String date = appointmentRegisteredUserDTO.getDate();
		String dateParts[] = date.split("T");
		String dateAndTime = dateParts[0] + ' ' + time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
		appointment.setDate(dateTime);
		appointmentRepository.save(appointment);
		
		return appointment;
		
	}

	@Override
	public List<Appointment> findAllByCenterId(Long id) {
		return appointmentRepository.findAppointmentsByCenterAdministratorMedicalCenterCenterId(id);
	}

	@Override
	public Appointment schedulePredefinedAppointment(Long appointmentId, Long registeredUserId) {
		Appointment schedulingAppointment = appointmentRepository.findByAppointmentId(appointmentId);
		schedulingAppointment.setIsAvailable(false);
		RegisteredUser registeredUser = (RegisteredUser)userService.findById(registeredUserId);
		schedulingAppointment.setRegisteredUser(registeredUser);
		appointmentRepository.save(schedulingAppointment);
		//emailService.sendNotificationForScheduledAppointment(registeredUser.getEmail(), schedulingAppointment);
		return schedulingAppointment;
	}
	
	@Override
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
		return cancelingAppointment;
	}

}
