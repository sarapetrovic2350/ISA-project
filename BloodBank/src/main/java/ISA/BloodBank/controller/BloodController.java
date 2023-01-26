package ISA.BloodBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ISA.BloodBank.dto.AddingBloodDTO;
import ISA.BloodBank.model.Blood;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.service.BloodService;

@RestController
@RequestMapping(value = "/blood", produces = MediaType.APPLICATION_JSON_VALUE)
public class BloodController {
	private BloodService bloodService;

	@Autowired
	public BloodController(BloodService bloodService) {
		super();
		this.bloodService = bloodService;
	}
	
	@PreAuthorize("hasRole('ROLE_CENTER_ADMINISTRATOR')")
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<Blood>> findAll() {
		return new ResponseEntity<List<Blood>>(bloodService.getAllBlood(),
				HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CENTER_ADMINISTRATOR')")
	@RequestMapping(value="/addBlood", method = RequestMethod.PUT)
	 public @ResponseBody Blood addBlood(@RequestBody AddingBloodDTO c) {
		 //System.out.println(c);
		 return bloodService.addingBlood(c);
	 }
	
	@PreAuthorize("hasRole('ROLE_CENTER_ADMINISTRATOR')")
	@GetMapping(value="/getBloodByCenterId/{centerId}")
	 public ResponseEntity<List<Blood>> getBloodsByCenterId(@PathVariable Long centerId) {
		return new ResponseEntity<List<Blood>>(bloodService.getBloodsByCenterId(centerId),
				HttpStatus.OK);
	 }
}
