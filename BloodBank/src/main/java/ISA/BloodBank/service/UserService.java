package ISA.BloodBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.UserRegistrationDTO;
import ISA.BloodBank.iservice.IUserService;
import ISA.BloodBank.model.RegisteredUser;
import ISA.BloodBank.model.User;
import ISA.BloodBank.model.UserType;
import ISA.BloodBank.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	
	private IUserRepository userRepository;
	
	@Autowired
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User registerUser(UserRegistrationDTO userRegistrationDTO) {
		
		RegisteredUser registeredUser = new RegisteredUser();
		registeredUser.setEmail(userRegistrationDTO.getEmail());
		registeredUser.setPassword(userRegistrationDTO.getPassword());
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
		userRepository.save(registeredUser);
		return registeredUser;
		
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}