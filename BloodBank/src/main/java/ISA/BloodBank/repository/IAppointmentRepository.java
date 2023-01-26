package ISA.BloodBank.repository;
import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{
	
	List<Appointment> findAppointmentsByCenterAdministratorMedicalCenterCenterId(Long id);
	List<Appointment> findAppointmentsByRegisteredUserUserId(Long id);
	Appointment findByAppointmentId(Long appointmentId);
	List<Appointment> findAll();
	List<Appointment> findAppointmentsByMedicalCenterCenterId(Long id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Appointment a where a.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Appointment findOneById(@Param("id")Long id);
}
