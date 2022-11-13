package ISA.BloodBank.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.MedicalCenter;

//@Repository
public interface IMedicalCenterRepository extends JpaRepository<MedicalCenter, Long> {
	
	//MedicalCenter findMedCenterById(Long centerId); 
	
//	@Query("SELECT mc FROM MedicalCenter mc WHERE mc.name=?1")
    List<MedicalCenter>findByName(String name);
	
	//@Query("SELECT mc FROM MedicalCenter mc, Address a WHERE mc.addressId=a.addressId and a.city=?1")
    List<MedicalCenter>findMedicalCentersByAddressCity(String city);

//	@Query("SELECT mc FROM MedicalCenter mc, Address a WHERE mc.addressId=a.addressId and mc.name=?1 and a.city=?2")
	List<MedicalCenter>findMedicalCentersByNameAndAddressCity(String name, String city);
}
