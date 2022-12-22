package ISA.BloodBank.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
public class AppointmentService implements IAppointmentService {
	
	private IAppointmentRepository appointmentRepository;
	
	private CenterAdministratorService centerAdministaratorService;
	
	private MedicalCenterService medicalCenterService;
	
	private UserService userService;
	
	@Autowired
	public AppointmentService(IAppointmentRepository appointmentRepository, @Lazy CenterAdministratorService centerAdministaratorService,
			UserService userService, MedicalCenterService medicalCenterService) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.centerAdministaratorService = centerAdministaratorService;
		this.userService = userService;
		this.medicalCenterService = medicalCenterService;
	}
	
	@Override
	public Appointment createPredefinedAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = new Appointment();
		CenterAdministrator centerAdministrator = new CenterAdministrator();
		centerAdministrator = centerAdministaratorService.findById(Long.parseLong(appointmentDTO.getAdministratorCenterID()));
		appointment.setCenterAdministrator(centerAdministrator);
		MedicalCenter medicalCenter = centerAdministrator.getMedicalCenter();
		appointment.setMedicalCenter(medicalCenter);
		appointment.setIsAvailable(true);
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
		//CenterAdministrator centerAdministrator = new CenterAdministrator();
		List<CenterAdministrator> administrators = centerAdministaratorService.GetFreeCenterAdministartior(Long.parseLong(appointmentRegisteredUserDTO.getMedicalCenterID()));
		MedicalCenter medicalCenter = new MedicalCenter();
		RegisteredUser registeredUser = new RegisteredUser();
		//centerAdministrator = centerAdministaratorService.findById(Long.parseLong(appointmentRegisteredUserDTO.getAdministratorCenterID()));
		medicalCenter  = medicalCenterService.findById(Long.parseLong(appointmentRegisteredUserDTO.getMedicalCenterID()));
		appointment.setCenterAdministrator(administrators.get(0));
		appointment.setMedicalCenter(medicalCenter);
		appointment.setIsAvailable(false);
		registeredUser = (RegisteredUser)userService.findById(Long.parseLong(appointmentRegisteredUserDTO.getRegisteredUserID()));
		appointment.setRegisteredUser(registeredUser);
		appointment.setDuration("15");
		String time = appointmentRegisteredUserDTO.getTime();
		String date = appointmentRegisteredUserDTO.getDate();
		//String dateParts[] = date.split("T");
		String dateAndTime = date + ' ' + time;
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
		return schedulingAppointment;
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
			}
			else {continue;}
		}
		
		return appointments;
	}
	

}
