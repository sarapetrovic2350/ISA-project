package ISA.BloodBank.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.ChangePasswordDTO;
import ISA.BloodBank.dto.SystemAdministratorRegistrationDTO;
import ISA.BloodBank.iservice.ISystemAdministratorService;
import ISA.BloodBank.model.Authority;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.SystemAdministrator;
import ISA.BloodBank.model.UserType;

import ISA.BloodBank.repository.ISystemAdministratorRepository;

@Service
public class SystemAdministratorService implements ISystemAdministratorService{
	
	private ISystemAdministratorRepository systemAdministratorRepository;
	
	private AuthorityService authorityService;
	
	@Autowired
	public SystemAdministratorService(ISystemAdministratorRepository systemAdministratorRepository, AuthorityService authorityService) {
		this.systemAdministratorRepository = systemAdministratorRepository;
		this.authorityService = authorityService;
	}
	
	@Override
	public List<SystemAdministrator> getAllSystemAdministrators(){
		return systemAdministratorRepository.findAll();
	}
	
	@Override
	public SystemAdministrator registerSystemAdministrator(
			SystemAdministratorRegistrationDTO systemAdministratorRegistrationDTO) {
		
		SystemAdministrator systemAdministrator = new SystemAdministrator();
		systemAdministrator.setEmail(systemAdministratorRegistrationDTO.getEmail());
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		systemAdministrator.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(systemAdministratorRegistrationDTO.getPassword(), encodedSalt);
		String securePassword = hashPassword(passwordWithSalt);
		systemAdministrator.setPassword(securePassword);
		systemAdministrator.setName(systemAdministratorRegistrationDTO.getName());
		systemAdministrator.setSurname(systemAdministratorRegistrationDTO.getSurname());
		systemAdministrator.setAddress(systemAdministratorRegistrationDTO.getAddress());
		systemAdministrator.setPhoneNumber(systemAdministratorRegistrationDTO.getPhoneNumber());
		systemAdministrator.setJmbg(systemAdministratorRegistrationDTO.getJmbg());
		systemAdministrator.setGender(systemAdministratorRegistrationDTO.getGender());
		systemAdministrator.setOccupation(systemAdministratorRegistrationDTO.getOccupation());
		systemAdministrator.setOccupationInfo(systemAdministratorRegistrationDTO.getOccupationInfo());
		systemAdministrator.setUserType(UserType.SYSTEM_ADMINISTRATOR);
		
		systemAdministrator.setEnabled(true);
		Authority authority = authorityService.findByName("ROLE_SYSTEM_ADMINISTRATOR");
		systemAdministrator.setAuthority(authority);
		systemAdministratorRepository.save(systemAdministrator);
		return systemAdministrator;
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
	
	/*public CenterAdministrator changePassword(ChangePasswordDTO newPassword) {
		CenterAdministrator centreAdmin = findByEmail(newPassword.getEmail()); 	
		checkInput(newPassword, centreAdmin);
		generateNewSecurePassword(newPassword, centreAdmin);
		return centerAdministratorRepository.save(centreAdmin);
	}
	
	private void checkInput(ChangePasswordDTO changePasswordDTO, CenterAdministrator centreAdmin) {
		if (changePasswordDTO.getPassword().equals(changePasswordDTO.getOldPassword())) {
			throw new IllegalArgumentException("Password can not be the same as the old one.");
		}
		if (!changePasswordDTO.getPassword().equals(changePasswordDTO.getPasswordRepeated())) {
			throw new IllegalArgumentException("Passwords must match!");
		}
		if (changePasswordDTO.getPassword().isEmpty() || changePasswordDTO.getPasswordRepeated().isEmpty()
				|| changePasswordDTO.getOldPassword().isEmpty()) {
			throw new IllegalArgumentException("Fill all the required fields!");
		}
		
		
		String oldPassword = generatePasswordWithSalt(changePasswordDTO.getOldPassword(), centreAdmin.getSalt());
		if(!verifyHash(oldPassword, centreAdmin.getPassword())) {
			throw new IllegalArgumentException("Old password isn't correct!");
		}
	}
	
	private void generateNewSecurePassword(ChangePasswordDTO changePasswordDTO, CenterAdministrator centreAdmin) {
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		centreAdmin.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(changePasswordDTO.getPassword(), encodedSalt);
		String newSecurePassword = hashPassword(passwordWithSalt);
		centreAdmin.setPassword(newSecurePassword);
	}
	
	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}*/

}
