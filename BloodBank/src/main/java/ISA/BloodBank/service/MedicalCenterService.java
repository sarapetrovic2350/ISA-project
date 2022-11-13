package ISA.BloodBank.service;

import java.util.List;
import java.util.Optional;

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
		medCenter.setCenterAdministrators(medicalCenter.getCenterAdministrators());
		
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
	
	public MedicalCenter findById(Long id) throws AccessDeniedException {
		MedicalCenter u = medicalCenterRepository.findById(id).orElseGet(null);
		return u;
	}
	
}
