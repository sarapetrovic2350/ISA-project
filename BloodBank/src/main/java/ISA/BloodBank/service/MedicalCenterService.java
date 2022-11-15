package ISA.BloodBank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.iservice.IMedicalCenterService; 
import ISA.BloodBank.repository.IMedicalCenterRepository;
import ISA.BloodBank.model.MedicalCenter; 

@Service
public class MedicalCenterService implements IMedicalCenterService{

private IMedicalCenterRepository medicalCenterRepository;
	
	@Autowired
	public MedicalCenterService(IMedicalCenterRepository medicalCenterRepository) {
		this.medicalCenterRepository = medicalCenterRepository;
	}
	
	@Override
	public MedicalCenter save(MedicalCenter medicalCenter) {
		
		MedicalCenter medCenter = new MedicalCenter(); 
		medCenter.setCenterId(medicalCenter.getCenterId()); 
		medCenter.setName(medicalCenter.getName());
		medCenter.setDescription(medicalCenter.getDescription());
		medCenter.setAverageGrade(medicalCenter.getAverageGrade());
		medCenter.setName(medicalCenter.getName());
		medCenter.setAddress(medicalCenter.getAddress());
		//medCenter.setCenterAdministrators(medicalCenter.getCenterAdministrators());
		
		medicalCenterRepository.save(medCenter);
		return medCenter;
	}

	@Override
	public List<MedicalCenter> getAll() {

		return medicalCenterRepository.findAll();
	}

//	@Override
//	public MedicalCenter update(MedicalCenter medicalCenter) {
//		MedicalCenter medCenter = medicalCenterRepository.findMedCenterById(medicalCenter.getCenterId());
//		
//		if(medCenter == null) {
//			return null;
//		}
//		medCenter.setCenterId(medicalCenter.getCenterId()); 
//		medCenter.setName(medicalCenter.getName());
//		medCenter.setDescription(medicalCenter.getDescription());
//		medCenter.setAverageGrade(medicalCenter.getAverageGrade());
//		medCenter.setName(medicalCenter.getName());
//		medCenter.setAddress(medicalCenter.getAddress());
//		medCenter.setCenterAdministrators(medicalCenter.getCenterAdministrators());
//		
//		medicalCenterRepository.save(medCenter);
//		return medCenter;
//	}
	

}
