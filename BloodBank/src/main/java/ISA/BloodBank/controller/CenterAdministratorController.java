package ISA.BloodBank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.dto.CenterAdministratorRegistrationDTO;
import ISA.BloodBank.dto.CenterAdministratorUpdateDTO;
import ISA.BloodBank.dto.ChangePasswordDTO;
import ISA.BloodBank.dto.UserUpdateDTO;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.model.User;
import ISA.BloodBank.service.CenterAdministratorService;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/centerAdministrator", produces = MediaType.APPLICATION_JSON_VALUE)
public class CenterAdministratorController {

	private CenterAdministratorService centerAdministratorService;

	@Autowired
	public CenterAdministratorController(CenterAdministratorService centerAdministratorService) {
		super();
		this.centerAdministratorService = centerAdministratorService;
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping(value = "/registerCenterAdministrator")
	public ResponseEntity<?> registerCenterAdministrator(
			@RequestBody CenterAdministratorRegistrationDTO centerAdministratorRegistrationDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			// add validations
			return new ResponseEntity<>(
					centerAdministratorService.registerCenterAdministrator(centerAdministratorRegistrationDTO),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<CenterAdministrator>> findAll() {
		return new ResponseEntity<List<CenterAdministrator>>(centerAdministratorService.getAllCenterAdministrator(),
				HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CENTER_ADMINISTRATOR')")
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	 public @ResponseBody CenterAdministratorUpdateDTO update(@RequestBody CenterAdministratorUpdateDTO c) {
		 System.out.println(c);
		 return centerAdministratorService.update(c);
	 }
	
	 @GetMapping(value="/getCenterAdministratorById/{adminId}")
	 public CenterAdministrator loadById(@PathVariable Long adminId) {
		return this.centerAdministratorService.findById(adminId);
	 }
	 
	 @GetMapping(value="/getCenterAdministratorByEmail/{email}")
	 public CenterAdministrator findByEmail(@PathVariable String email) {
		return this.centerAdministratorService.findByEmail(email);
	 }
	 
	 @GetMapping(value="/getMedicalCenterByAdminEmail/{email}")
	 public MedicalCenter findCenterByAdminEmail(@PathVariable String email) {
		return this.centerAdministratorService.getMedicalCenter(email);
	 }
	 
	 @GetMapping(value="/getCenterAdministratorsByCenterId/{centerId}")
	 public ArrayList<CenterAdministrator> getAdministratorsByCenterId(@PathVariable Long centerId) {
		return this.centerAdministratorService.getAllAdministratorsByCenterId(centerId);
	 }
	 
	 @RequestMapping(value="/changePassword", method = RequestMethod.PUT)
	    public @ResponseBody CenterAdministrator changePassword(@RequestBody ChangePasswordDTO dto) {

		 //CenterAdministrator centAdmin = centerAdministratorService.findByEmail(dto.getEmail());
		// CenterAdministrator retVal = centerAdministratorService.changePassword(dto);
//	        if(u == null){
//	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	        }
//	        return new ResponseEntity<>(u,HttpStatus.OK);
	        return centerAdministratorService.changePassword(dto);
	        
	    }
	 
	 @PreAuthorize("hasRole('ROLE_CENTER_ADMINISTRATOR)")
	 @PostMapping(value = "/changePasswordFirstLogin")
		public ResponseEntity<User> changePasswordFirstLogin(@RequestBody ChangePasswordDTO changePasswordDTO) {
			return new ResponseEntity<User>(centerAdministratorService.changePasswordFirstLogin(changePasswordDTO), HttpStatus.OK);
		}

}
