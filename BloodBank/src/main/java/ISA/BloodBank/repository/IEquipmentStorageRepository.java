package ISA.BloodBank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.EquipmentStorage;

@Repository
public interface IEquipmentStorageRepository extends JpaRepository<EquipmentStorage, Long>{

}
