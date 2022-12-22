package ISA.BloodBank.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.Appointment;
import ISA.BloodBank.model.MedicalCenter;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{
	
	List<Appointment> findAppointmentsByCenterAdministratorMedicalCenterCenterId(Long id);
	Appointment findByAppointmentId(Long appointmentId);
	List<Appointment> findAll();
	List<Appointment> findAppointmentsByMedicalCenterCenterId(Long id);
}
