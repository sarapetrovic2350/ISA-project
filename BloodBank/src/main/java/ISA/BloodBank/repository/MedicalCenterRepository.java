package ISA.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.MedicalCenter;

@Repository
public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Long> {

}
