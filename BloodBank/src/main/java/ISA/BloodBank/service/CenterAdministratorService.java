package ISA.BloodBank.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.CenterAdministratorRegistrationDTO;
import ISA.BloodBank.dto.CenterAdministratorUpdateDTO;
import ISA.BloodBank.iservice.ICenterAdministratorService;
import ISA.BloodBank.model.Authority;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.UserType;
import ISA.BloodBank.repository.ICenterAdministratorRepository;

@Service
public class CenterAdministratorService implements ICenterAdministratorService{

	private ICenterAdministratorRepository centerAdministratorRepository;
	
	private AuthorityService authorityService;
	
	@Autowired
	public CenterAdministratorService(ICenterAdministratorRepository centerAdministratorRepository, AuthorityService authorityService) {
		this.centerAdministratorRepository = centerAdministratorRepository;
		this.authorityService = authorityService;
	}

	@Override
	public CenterAdministrator registerCenterAdministrator(
			CenterAdministratorRegistrationDTO centerAdministratorRegistrationDTO) {
		
		CenterAdministrator centerAdministrator = new CenterAdministrator();
		centerAdministrator.setEmail(centerAdministratorRegistrationDTO.getEmail());
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		centerAdministrator.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(centerAdministratorRegistrationDTO.getPassword(), encodedSalt);
		String securePassword = hashPassword(passwordWithSalt);
		centerAdministrator.setPassword(securePassword);
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
		centerAdministrator.setEnabled(true);
		Authority authority = authorityService.findByName("ROLE_CENTER_ADMINISTRATOR");
		centerAdministrator.setAuthority(authority);
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
	
	private static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] genSalt = new byte[16];
		random.nextBytes(genSalt);
		return genSalt;
	}

	private String generatePasswordWithSalt(String userPassword, String salt) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(userPassword);
		stringBuilder.append(salt);
		return stringBuilder.toString();
	}

	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
}
