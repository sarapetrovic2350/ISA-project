package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.UserRegistrationDTO;
import ISA.BloodBank.model.ConfirmationToken;
import ISA.BloodBank.model.JwtAuthenticationRequest;
import ISA.BloodBank.dto.UserUpdateDTO;
import ISA.BloodBank.model.User;

@Service
public interface IUserService {
	User registerUser(UserRegistrationDTO userRegistrationDTO);

	User login(JwtAuthenticationRequest authenticationRequest);

	List<User> getAllUsers();

	User findLoggedInUser();

	User findById(Long id);
	
	UserUpdateDTO updateUser(UserUpdateDTO user);
	
	void sendConfirmationEmail(User user, ConfirmationToken confirmationToken);
}
