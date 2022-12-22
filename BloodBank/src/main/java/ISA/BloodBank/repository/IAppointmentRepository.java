package ISA.BloodBank.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{
	
	List<Appointment> findAppointmentsByCenterAdministratorMedicalCenterCenterId(Long id);
	List<Appointment> findAppointmentsByRegisteredUserUserId(Long id);
	Appointment findByAppointmentId(Long appointmentId);
}
