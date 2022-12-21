package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.AppointmentDTO;
import ISA.BloodBank.dto.AppointmentRegisteredUserDTO;
import ISA.BloodBank.model.Appointment;

@Service
public interface IAppointmentService {
	
	Appointment createPredefinedAppointment(AppointmentDTO appointmentDTO);
	
	Appointment createAppointmentRegisteredUser(AppointmentRegisteredUserDTO appointmentRegisteredUserDTO);

	List<Appointment> findAllByCenterId(Long id);
	
	Appointment schedulePredefinedAppointment(Long appointmentId, Long registeredUserId);
}
