package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;
import ISA.BloodBank.model.MedicalCenter; 

@Service
public interface IMedicalCenterService {

	MedicalCenter save(MedicalCenter medicalCenter);
	//MedicalCenter update(MedicalCenter medicalCenter); 
	List<MedicalCenter> getAll();
}
