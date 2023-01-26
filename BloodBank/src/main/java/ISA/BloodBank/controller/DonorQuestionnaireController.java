package ISA.BloodBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.dto.DonorQuestionnaireDTO;
import ISA.BloodBank.model.DonorQuestionnaire;
import ISA.BloodBank.service.DonorQuestionnaireService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/donorQuestionnaire", produces = MediaType.APPLICATION_JSON_VALUE)
public class DonorQuestionnaireController {

	private DonorQuestionnaireService donorQuestionnaireService;

	@Autowired
	public DonorQuestionnaireController(DonorQuestionnaireService donorQuestionnaireService) {
		super();
		this.donorQuestionnaireService = donorQuestionnaireService;
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTERED_USER')")
	@PostMapping(value = "/saveQuestionnaire")
	public ResponseEntity<?> saveQuestionnaire(@RequestBody DonorQuestionnaireDTO donorQuestionnaireDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			return new ResponseEntity<>(donorQuestionnaireService.saveQuestionnaire(donorQuestionnaireDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<DonorQuestionnaire>> findAll() {
		return new ResponseEntity<List<DonorQuestionnaire>>(donorQuestionnaireService.getAllQuestionnaires(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkQuestionnaire/{userId}")
	public ResponseEntity<?> checkQuestionnaire(@PathVariable Long userId, UriComponentsBuilder uriComponentsBuilder) {
		if(donorQuestionnaireService.checkQuestionnare(userId) == true ) {
			return new ResponseEntity<DonorQuestionnaire>(donorQuestionnaireService.getQuestionnareByUserId(userId), HttpStatus.OK);
		}else {
			return new ResponseEntity<DonorQuestionnaire>(HttpStatus.NOT_FOUND);
		}	
	}
	
}
