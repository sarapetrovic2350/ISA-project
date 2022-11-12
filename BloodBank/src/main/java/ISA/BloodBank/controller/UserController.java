package ISA.BloodBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.dto.UserRegistrationDTO;
import ISA.BloodBank.dto.UserUpdateDTO;
import ISA.BloodBank.model.User;
import ISA.BloodBank.service.UserService;
	

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping(value = "/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			// add validations
			return new ResponseEntity<>(userService.registerUser(userRegistrationDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	 @RequestMapping(value="/update", method = RequestMethod.PUT)
	 public @ResponseBody UserUpdateDTO update(@RequestBody UserUpdateDTO u) {
		 return userService.updateUser(u);
	 }
	
}
