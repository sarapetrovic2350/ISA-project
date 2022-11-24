package ISA.BloodBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.exception.ResourceConflictException;
import ISA.BloodBank.dto.ChangePasswordDTO;
import ISA.BloodBank.dto.UserRegistrationDTO;
import ISA.BloodBank.dto.UserUpdateDTO;
import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.model.User;
import ISA.BloodBank.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping(value = "/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		User existUser = this.userService.findByEmail(userRegistrationDTO.getEmail());
		if (existUser != null) {
			throw new ResourceConflictException(userRegistrationDTO.getEmail(), "Email already exists");
		}
		try {
			return new ResponseEntity<>(userService.registerUser(userRegistrationDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllRegistredUsers")
	public ResponseEntity<List<User>> findAllRegistredUsers() {
		return new ResponseEntity<List<User>>(userService.getAllRegistredUsers(), HttpStatus.OK);
	}
	
	 @PutMapping(value="/update")
	 public @ResponseBody UserUpdateDTO update(@RequestBody UserUpdateDTO u) {
		 return userService.updateUser(u);
	 }
	 
	 @GetMapping(value="/getUserById/{userId}")
	 public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	 }
	 
	 @GetMapping(value="/getUserByEmail/{email}")
	 public User findById(@PathVariable String email) {
		return this.userService.findByEmail(email);
	 }
	 
	 @RequestMapping(value="/changePassword", method = RequestMethod.PUT)
	    public @ResponseBody User changePassword(@RequestBody ChangePasswordDTO dto) {
		 return userService.changePassword(dto);
	    }
	 
	 @GetMapping(value = "/findUserByNameAndSurnameForSystemAdmin/{name}/{surname}")
		public ResponseEntity<List<User>> findUserByNameAndSurname(@PathVariable String name,@PathVariable String surname){
			return new ResponseEntity<List<User>>(userService.findUserByNameAndSurnameForSystemAdmin(name, surname), HttpStatus.OK);
		}
	 
	 @GetMapping(value = "/findUserByNameAndSurnameForCenterAdmin/{name}/{surname}")
		public ResponseEntity<List<User>> findUserByNameAndSurnameForCenterAdmin(@PathVariable String name,@PathVariable String surname){
			return new ResponseEntity<List<User>>(userService.findUserByNameAndSurnameForCenterAdmin(name, surname), HttpStatus.OK);
		}
	
}
