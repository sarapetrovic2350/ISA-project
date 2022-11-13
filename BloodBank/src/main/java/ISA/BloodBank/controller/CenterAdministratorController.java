package ISA.BloodBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.dto.CenterAdministratorRegistrationDTO;
import ISA.BloodBank.dto.CenterAdministratorUpdateDTO;
import ISA.BloodBank.dto.UserUpdateDTO;
import ISA.BloodBank.model.CenterAdministrator;
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

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody CenterAdministratorUpdateDTO update(@RequestBody CenterAdministratorUpdateDTO c) {
		return centerAdministratorService.update(c);
	}
}
