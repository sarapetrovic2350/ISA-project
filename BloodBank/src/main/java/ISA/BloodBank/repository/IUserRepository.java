package ISA.BloodBank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByUserId(Long userId);
	
}
