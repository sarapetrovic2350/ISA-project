package ISA.BloodBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.CenterAdministratorRegistrationDTO;
import ISA.BloodBank.dto.CenterAdministratorUpdateDTO;
import ISA.BloodBank.iservice.ICenterAdministratorService;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.model.User;
import ISA.BloodBank.model.UserType;
import ISA.BloodBank.repository.ICenterAdministratorRepository;

@Service
public class CenterAdministratorService implements ICenterAdministratorService{

	private ICenterAdministratorRepository centerAdministratorRepository;
	
	@Autowired
	public CenterAdministratorService(ICenterAdministratorRepository centerAdministratorRepository) {
		this.centerAdministratorRepository = centerAdministratorRepository;
	}

	@Override
	public CenterAdministrator registerCenterAdministrator(
			CenterAdministratorRegistrationDTO centerAdministratorRegistrationDTO) {
		
		CenterAdministrator centerAdministrator = new CenterAdministrator();
		centerAdministrator.setEmail(centerAdministratorRegistrationDTO.getEmail());
		centerAdministrator.setPassword(centerAdministratorRegistrationDTO.getPassword());
		centerAdministrator.setName(centerAdministratorRegistrationDTO.getName());
		centerAdministrator.setSurname(centerAdministratorRegistrationDTO.getSurname());
		centerAdministrator.setAddress(centerAdministratorRegistrationDTO.getAddress());
		centerAdministrator.setPhoneNumber(centerAdministratorRegistrationDTO.getPhoneNumber());
		centerAdministrator.setJmbg(centerAdministratorRegistrationDTO.getJmbg());
		centerAdministrator.setGender(centerAdministratorRegistrationDTO.getGender());
		centerAdministrator.setOccupation(centerAdministratorRegistrationDTO.getOccupation());
		centerAdministrator.setOccupationInfo(centerAdministratorRegistrationDTO.getOccupationInfo());
		centerAdministrator.setMedicalCenter(centerAdministratorRegistrationDTO.getMedicalCenter());
		centerAdministrator.setUserType(UserType.CENTER_ADMINISTRATOR);
		centerAdministratorRepository.save(centerAdministrator);
		return centerAdministrator;
	}

	@Override
	public List<CenterAdministrator> getAllCenterAdministrator() {
		return centerAdministratorRepository.findAll();
	}
	
	public CenterAdministratorUpdateDTO update(CenterAdministratorUpdateDTO centerAdmDto) {
		System.out.println("nesto"); 
		CenterAdministrator centAdm = (CenterAdministrator) centerAdministratorRepository.findById(centerAdmDto.getUserId()).get();
		centAdm.setEmail(centerAdmDto.getEmail());
		centAdm.setPassword(centerAdmDto.getPassword());
		centAdm.setName(centerAdmDto.getName());
		centAdm.setSurname(centerAdmDto.getSurname());
		centAdm.setAddress(centerAdmDto.getAddress());
		centAdm.setPhoneNumber(centerAdmDto.getPhoneNumber());
		centAdm.setJmbg(centerAdmDto.getJmbg());
		centAdm.setGender(centerAdmDto.getGender());
		centAdm.setOccupation(centerAdmDto.getOccupation());
		centAdm.setOccupationInfo(centerAdmDto.getOccupationInfo());
		
		centerAdministratorRepository.save(centAdm);
		
		return centerAdmDto; 
		
	}
	
	public CenterAdministrator findById(Long id) throws AccessDeniedException {
		CenterAdministrator centerAdministrator = centerAdministratorRepository.findById(id).orElseGet(null);
		return centerAdministrator;
	}
	
	public CenterAdministrator findByEmail(String email) {
		return centerAdministratorRepository.findByEmail(email); 
	}
	
	public MedicalCenter getMedicalCenter(String email) {
		CenterAdministrator centAdmin = findByEmail(email); 
		
		return centAdmin.getMedicalCenter(); 
		
	}
}
