package ISA.BloodBank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{

}
