package ISA.BloodBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.service.MedicalCenterService;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/medicalCenter", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalCenterController {

	private MedicalCenterService medicalCenterService;
	
	@Autowired
	public MedicalCenterController(MedicalCenterService medicalCenterService) {
		super();
		this.medicalCenterService = medicalCenterService;
	}
	
	@PostMapping(value = "/createCenter")
	public ResponseEntity<?> createCenter(@RequestBody MedicalCenter medicalCenter,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			// add validations
			return new ResponseEntity<>(medicalCenterService.save(medicalCenter), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<MedicalCenter>> findAll() {
		return new ResponseEntity<List<MedicalCenter>>(medicalCenterService.getAll(), HttpStatus.OK);
	}
	
//	@PutMapping(value = "/updateCenter")
//	public ResponseEntity<?> updateCenter(@RequestBody MedicalCenter medicalCenter,
//			UriComponentsBuilder uriComponentsBuilder) {
//		try {
//			// add validations
//			return new ResponseEntity<>(medicalCenterService.update(medicalCenter), HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
}
