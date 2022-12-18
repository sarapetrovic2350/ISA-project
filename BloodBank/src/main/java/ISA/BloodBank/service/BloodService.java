package ISA.BloodBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.AddingBloodDTO;
import ISA.BloodBank.iservice.IBloodService;
import ISA.BloodBank.model.Blood;
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
		// TODO Auto-generated method stub
		Blood blood = (Blood) bloodRepository.findById(addingBloodDTO.getBloodId()).get();
		Double newQuantaty = blood.getQuantaty() + addingBloodDTO.getQuantaty(); 
		blood.setQuantaty(newQuantaty); 
		bloodRepository.save(blood); 
		return blood;
	}
	
	

}
