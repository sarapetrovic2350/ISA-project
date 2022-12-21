package ISA.BloodBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.dto.AppointmentDTO;
import ISA.BloodBank.dto.AppointmentRegisteredUserDTO;
import ISA.BloodBank.model.DonorQuestionnaire;
import ISA.BloodBank.service.AppointmentService;
import ISA.BloodBank.service.DonorQuestionnaireService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	private DonorQuestionnaireService donorQuestionnaireService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService, DonorQuestionnaireService donorQuestionnaireService) {
		super();
		this.appointmentService = appointmentService;
		this.donorQuestionnaireService = donorQuestionnaireService;
	}

	@PostMapping(value = "/createPredefinedAppointment")
	public ResponseEntity<?> createPredefinedAppointment(@RequestBody AppointmentDTO appointmentDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			return new ResponseEntity<>(
					appointmentService.createPredefinedAppointment(appointmentDTO),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/createAppointmentRegisteredUser")
	public ResponseEntity<?> createAppointmentRegisteredUser(@RequestBody AppointmentRegisteredUserDTO appointmentRegisteredUserDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			DonorQuestionnaire donorQuestionnaire = donorQuestionnaireService.getQuestionnareByUserId(Long.parseLong(appointmentRegisteredUserDTO.getRegisteredUserID()));
			if(donorQuestionnaire.getQuestionnaireId() != null) {
				if(donorQuestionnaire.getRecentlyDonatedBlood() == false) {
					return new ResponseEntity<>(
					appointmentService.createAppointmentRegisteredUser(appointmentRegisteredUserDTO),
					HttpStatus.CREATED);
				}else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
