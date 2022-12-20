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
import ISA.BloodBank.service.AppointmentService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		super();
		this.appointmentService = appointmentService;
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

}