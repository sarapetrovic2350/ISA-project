package ISA.BloodBank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.AddingBloodDTO;
import ISA.BloodBank.iservice.IBloodService;
import ISA.BloodBank.model.Blood;
import ISA.BloodBank.model.BloodType;
import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.repository.IBloodRepository;

@Service
public class BloodService implements IBloodService{
	
	private IBloodRepository bloodRepository; 
	
	@Autowired
	public BloodService(IBloodRepository bloodRepository) {
		this.bloodRepository = bloodRepository;
	}

	@Override
	public List<Blood> getAllBlood() {
		// TODO Auto-generated method stub
		return bloodRepository.findAll();
	}

	@Override
	public Blood addingBlood(AddingBloodDTO addingBloodDTO) {
		Blood blood = findBloodByTypeCenterId(addingBloodDTO.getBloodType() ,addingBloodDTO.getCenterId());
		Double newQuantaty = blood.getQuantaty() + addingBloodDTO.getQuantaty(); 
		blood.setQuantaty(newQuantaty); 
		bloodRepository.save(blood); 
		return blood;
		//return null; 
	}

	@Override
	public List<Blood> getBloodsByCenterId(Long centerId) {

		List<Blood> bloodFinds = new ArrayList<Blood>();
		List<Blood> allBloods = getAllBlood();
		for (Blood blood : allBloods) {
			if(blood.getMedicalCenter().getCenterId().equals(centerId)) {
				bloodFinds.add(blood);
			}
		}
		return bloodFinds;
	}

	@Override
	public Blood findBloodByTypeCenterId(BloodType bloodType, Long centerId) {
		// TODO Auto-generated method stub
		Blood bloodRet = new Blood(); 
		List<Blood> allBloods = getAllBlood();
		for (Blood blood : allBloods) {
			if(blood.getMedicalCenter().getCenterId().equals(centerId) && blood.getBloodType().equals(bloodType)) {
				bloodRet = blood;
			}
		}
		return bloodRet;
	}


}
