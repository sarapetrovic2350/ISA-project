package ISA.BloodBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.CenterAdministratorRegistrationDTO;
import ISA.BloodBank.iservice.ICenterAdministratorService;
import ISA.BloodBank.model.CenterAdministrator;
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
}
