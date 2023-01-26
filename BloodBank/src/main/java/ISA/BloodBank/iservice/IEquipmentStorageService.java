package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.EquipmentStorage;

@Service
public interface IEquipmentStorageService {

	void updateQuantaty(Long centerId, Double qunataty); 
	List<EquipmentStorage> getAll(); 
	EquipmentStorage findEquipmentByCenterId(Long Id);
}
