package ISA.BloodBank.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.AppointmentDTO;
import ISA.BloodBank.iservice.IAppointmentService;
import ISA.BloodBank.model.Appointment;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.repository.IAppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService {
	
	private IAppointmentRepository appointmentRepository;
	
	private CenterAdministratorService centerAdministaratorService;
	
	@Autowired
	public AppointmentService(IAppointmentRepository appointmentRepository, CenterAdministratorService centerAdministaratorService) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.centerAdministaratorService = centerAdministaratorService;
	}
	
	@Override
	public Appointment createPredefinedAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = new Appointment();
		CenterAdministrator centerAdministrator = new CenterAdministrator();
		centerAdministrator = centerAdministaratorService.findById(Long.parseLong(appointmentDTO.getAdministratorCenterID()));
		appointment.setCenterAdministrator(centerAdministrator);
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

}
