package ISA.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

}
