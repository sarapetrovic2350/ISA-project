package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.AddingBloodDTO;
import ISA.BloodBank.model.Blood;
import ISA.BloodBank.model.BloodType;

@Service
public interface IBloodService {
	List<Blood> getAllBlood();
	Blood addingBlood(AddingBloodDTO addingBloodDTO);
	List<Blood> getBloodsByCenterId(Long centerId); 
	Blood findBloodByTypeCenterId(BloodType bloodType, Long centerId); 
	
}
