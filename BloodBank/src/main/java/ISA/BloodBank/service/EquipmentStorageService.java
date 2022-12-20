package ISA.BloodBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.iservice.IEquipmentStorageService;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.EquipmentStorage;
import ISA.BloodBank.model.Report;
import ISA.BloodBank.repository.IEquipmentStorageRepository;

@Service
public class EquipmentStorageService  implements IEquipmentStorageService{
	
	private IEquipmentStorageRepository equipmentStorageRepository; 
	
	@Autowired
	public EquipmentStorageService(IEquipmentStorageRepository equipmentStorageRepository) {
		super();
		this.equipmentStorageRepository = equipmentStorageRepository;
	}

	@Override
	public void updateQuantaty(Long centerId, Double qunataty) {
		// TODO Auto-generated method stub
		EquipmentStorage equipment = findEquipmentByCenterId(centerId); 
		Double quantaty = equipment.getQuantaty() - qunataty; 
		equipment.setQuantaty(quantaty); 
		equipmentStorageRepository.save(equipment); 
		
	}

	@Override
	public List<EquipmentStorage> getAll() {
		return equipmentStorageRepository.findAll();
	}
	
	@Override
	public EquipmentStorage findEquipmentByCenterId(Long Id) {
		List<EquipmentStorage> list = getAll(); 
		for(int i =0; i<list.size(); i++) {
			if(list.get(i).getMedicalCenter().getCenterId().equals(Id)) {
				return list.get(i); 
			}
		}
		return null; 
	}
	
}
