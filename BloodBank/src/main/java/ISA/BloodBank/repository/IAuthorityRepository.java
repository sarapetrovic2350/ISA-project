package ISA.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.Authority;

@Repository
public interface IAuthorityRepository extends JpaRepository<Authority, Long> {

	Authority findByName(String name);
}
