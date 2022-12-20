package ISA.BloodBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ISA.BloodBank.dto.AddingBloodDTO;
import ISA.BloodBank.model.Blood;
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
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<Blood>> findAll() {
		return new ResponseEntity<List<Blood>>(bloodService.getAllBlood(),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/addBlood", method = RequestMethod.PUT)
	 public @ResponseBody Blood addBlood(@RequestBody AddingBloodDTO c) {
		 //System.out.println(c);
		 return bloodService.addingBlood(c);
	 }
}
