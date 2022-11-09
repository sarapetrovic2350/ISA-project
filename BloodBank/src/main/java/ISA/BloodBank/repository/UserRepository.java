package ISA.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
