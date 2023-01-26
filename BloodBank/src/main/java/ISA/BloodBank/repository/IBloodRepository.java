package ISA.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.Blood;

@Repository
public interface IBloodRepository extends JpaRepository<Blood, Long> {

}
