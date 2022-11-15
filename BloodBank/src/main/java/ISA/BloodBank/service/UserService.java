package ISA.BloodBank.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.UserRegistrationDTO;
import ISA.BloodBank.dto.UserUpdateDTO;
import ISA.BloodBank.iservice.IUserService;
import ISA.BloodBank.model.Authority;
import ISA.BloodBank.model.ConfirmationToken;
import ISA.BloodBank.model.JwtAuthenticationRequest;
import ISA.BloodBank.model.RegisteredUser;
import ISA.BloodBank.model.User;
import ISA.BloodBank.model.UserType;
import ISA.BloodBank.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	
	private IUserRepository userRepository;
	
	private AuthorityService authorityService;
	
	private ConfirmationTokenService confirmationTokenService;
	
	@Autowired
	public UserService(IUserRepository userRepository, AuthorityService authorityService,
			ConfirmationTokenService confirmationTokenService) {
		super();
		this.userRepository = userRepository;
		this.authorityService = authorityService;
		this.confirmationTokenService = confirmationTokenService;
	}
	
	@Override
	public User registerUser(UserRegistrationDTO userRegistrationDTO) {
		RegisteredUser registeredUser = new RegisteredUser();
		registeredUser.setEmail(userRegistrationDTO.getEmail());
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		registeredUser.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(userRegistrationDTO.getPassword(), encodedSalt);
		String securePassword = hashPassword(passwordWithSalt);
		registeredUser.setPassword(securePassword);
		registeredUser.setName(userRegistrationDTO.getName());
		registeredUser.setSurname(userRegistrationDTO.getSurname());
		registeredUser.setAddress(userRegistrationDTO.getAddress());
		registeredUser.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
		registeredUser.setJmbg(userRegistrationDTO.getJmbg());
		registeredUser.setGender(userRegistrationDTO.getGender());
		registeredUser.setOccupation(userRegistrationDTO.getOccupation());
		registeredUser.setOccupationInfo(userRegistrationDTO.getOccupationInfo());
		registeredUser.setUserType(UserType.REGISTERED_USER);
		registeredUser.setPenalties(0);
		registeredUser.setEnabled(true); 	// send confirmation email - false
		Authority authority = authorityService.findByName("ROLE_REGISTERED_USER");
		registeredUser.setAuthority(authority);
		userRepository.save(registeredUser);
		ConfirmationToken confirmationToken = confirmationTokenService.saveConfirmationToken(registeredUser);
		 /*send confirmation email
		*/
		return registeredUser;
		
	}

	@Override
	public User login(JwtAuthenticationRequest authenticationRequest) {
		User user = findByEmail(authenticationRequest.getEmail());
		if (user != null)
			if (verifyHash(generatePasswordWithSalt(authenticationRequest.getPassword(), user.getSalt()),
					user.getPassword()))
				return user;

		return null;
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

	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}

	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}
	
	public UserUpdateDTO updateUser(UserUpdateDTO user) {
		RegisteredUser u = (RegisteredUser) userRepository.findById(user.getUserId()).get();
		u.setName(user.getName());
		u.setSurname(user.getSurname());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setAddress(user.getAddress());
		u.setPhoneNumber(user.getPhoneNumber());
		u.setJmbg(user.getJmbg());
		u.setGender(user.getGender());
		u.setOccupation(user.getOccupation());
		u.setOccupationInfo(user.getOccupationInfo());
		u.setPenalties(user.getPenalties());
		u = this.userRepository.save(u);
		return user;
    }
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findLoggedInUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepository.findByUserId(user.getUserId());
	}
	
}