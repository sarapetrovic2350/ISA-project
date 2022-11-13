package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.UserRegistrationDTO;
import ISA.BloodBank.dto.UserUpdateDTO;
import ISA.BloodBank.model.User;


@Service
public interface IUserService {
	
	User registerUser(UserRegistrationDTO userRegistrationDTO);
	List<User> getAllUsers();
	User findById(Long id);
	UserUpdateDTO updateUser(UserUpdateDTO user);
}
