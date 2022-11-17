package ISA.BloodBank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
		//medCenter.setCenterAdministrators(medicalCenter.getCenterAdministrators());
		
		medicalCenterRepository.save(medCenter);
		return medCenter;
	}

	@Override
	public List<MedicalCenter> getAll() {

		return medicalCenterRepository.findAll();
	}

	public MedicalCenter update(MedicalCenter medCenterDto) {
		
		MedicalCenter medCenter = (MedicalCenter) medicalCenterRepository.findById(medCenterDto.getCenterId()).get();
		medCenter.setCenterId(medCenterDto.getCenterId()); 
		medCenter.setName(medCenterDto.getName());
		medCenter.setDescription(medCenterDto.getDescription());
		medCenter.setAverageGrade(medCenterDto.getAverageGrade());
		medCenter.setName(medCenterDto.getName());
		//System.out.println(medCenterDto.getAdress());
		medCenter.setAddress(medCenterDto.getAddress());
		//System.out.println(medCenter.getAddress()); 
		//medCenter.setCenterAdministrators(medCenterDto.getCenterAdministrators());
		medicalCenterRepository.save(medCenter);
		
		return medCenterDto; 
	}

	 public List<MedicalCenter> findMedicalCenterByNameAndPlace(String name, String place) {	
			List<MedicalCenter> medicalCentersFind = new ArrayList<MedicalCenter>();
//	        if(name.equals("null") && !place.equals("null"))
//	        	medicalCentersFind = medicalCenterRepository.findMedicalCentersByAddressCity(place);
//	        else if(!name.equals("null") && place.equals("null"))
//	        	medicalCentersFind = medicalCenterRepository.findByName(name);
//	        else {
//	        	medicalCentersFind = medicalCenterRepository.findMedicalCentersByNameAndAddressCity(name, place);
//	        }
//			return medicalCentersFind;
			List<MedicalCenter> medicalCenters = getAll();
			for (MedicalCenter medicalCenter : medicalCenters) {
				if(name.equals("null") || place.equals("null")) {
					if(medicalCenter.getName().toLowerCase().contains(name.toLowerCase().trim()) || medicalCenter.getAddress().getCity().toLowerCase().contains(place.toLowerCase().trim()))
						medicalCentersFind.add(medicalCenter);
				}else {
					if(medicalCenter.getName().toLowerCase().contains(name.toLowerCase().trim()) && medicalCenter.getAddress().getCity().toLowerCase().contains(place.toLowerCase().trim()))
						medicalCentersFind.add(medicalCenter);
				}
			}
			return medicalCentersFind;
		}
	 
	 public List<MedicalCenter> filterMedicalCenter(String name, String place, String grade) {
		 List<MedicalCenter> medicalCentersFind = findMedicalCenterByNameAndPlace(name, place);
		 List<MedicalCenter> filteredMedicalCenters = new ArrayList<MedicalCenter>();
		 for(MedicalCenter medicalCenter : medicalCentersFind) {
			 String[] gradeParts = grade.split("-");
			 if(medicalCenter.getAverageGrade() >= Double.valueOf(gradeParts[0]) &&  medicalCenter.getAverageGrade() <= Double.valueOf(gradeParts[1])) {
				 filteredMedicalCenters.add(medicalCenter);
			 }
		 }
		 return filteredMedicalCenters;
		 
	 }
	
	public MedicalCenter findById(Long id) throws AccessDeniedException {
		MedicalCenter u = medicalCenterRepository.findById(id).orElseGet(null);
		return u;
	}
}
