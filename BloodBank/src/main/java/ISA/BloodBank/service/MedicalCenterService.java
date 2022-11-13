package ISA.BloodBank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.MedicalCenterUpdateDTO;
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
		medCenter.setCenterAdministrators(medicalCenter.getCenterAdministrators());
		
		medicalCenterRepository.save(medCenter);
		return medCenter;
	}

	@Override
	public List<MedicalCenter> getAll() {

		return medicalCenterRepository.findAll();
	}

	public MedicalCenterUpdateDTO update(MedicalCenterUpdateDTO medCenterDto) {
		
		MedicalCenter medCenter = (MedicalCenter) medicalCenterRepository.findById(medCenterDto.getCenterId()).get();
		medCenter.setCenterId(medCenterDto.getCenterId()); 
		medCenter.setName(medCenterDto.getName());
		medCenter.setDescription(medCenterDto.getDescription());
		medCenter.setAverageGrade(medCenterDto.getAverageGrade());
		medCenter.setName(medCenterDto.getName());
		medCenter.setAddress(medCenterDto.getAdress());
		//medCenter.setCenterAdministrators(medCenterDto.getCenterAdministrators());
		medicalCenterRepository.save(medCenter);
		
		return medCenterDto; 
	}
	
	 public List<MedicalCenter> findMedicalCenterByNameAndPlace(String name, String place) {	
			List<MedicalCenter> medicalCentersFind = new ArrayList<MedicalCenter>();
	        if(name.equals("null") && !place.equals("null"))
	        	medicalCentersFind = medicalCenterRepository.findMedicalCentersByAddressCity(place);
	        else if(!name.equals("null") && place.equals("null"))
	        	medicalCentersFind = medicalCenterRepository.findByName(name);
	        else {
	        	medicalCentersFind = medicalCenterRepository.findMedicalCentersByNameAndAddressCity(name, place);
	        }
			return medicalCentersFind;
		}
	

}
