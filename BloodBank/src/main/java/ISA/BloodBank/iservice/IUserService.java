package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.UserRegistrationDTO;
import ISA.BloodBank.model.JwtAuthenticationRequest;
import ISA.BloodBank.model.User;

@Service
public interface IUserService {
	User registerUser(UserRegistrationDTO userRegistrationDTO);

	User login(JwtAuthenticationRequest authenticationRequest);

	List<User> getAllUsers();

	User findLoggedInUser();
}
