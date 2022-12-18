package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.AddingBloodDTO;
import ISA.BloodBank.model.Blood;

@Service
public interface IBloodService {
	List<Blood> getAllBlood();
	Blood addingBlood(AddingBloodDTO addingBloodDTO);
}
