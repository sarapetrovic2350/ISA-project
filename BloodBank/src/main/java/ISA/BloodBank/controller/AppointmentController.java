package ISA.BloodBank.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.dto.AppointmentDTO;
import ISA.BloodBank.dto.AppointmentRegisteredUserDTO;
import ISA.BloodBank.dto.PredefinedAppointmentDTO;
import ISA.BloodBank.dto.ScheduledAppointmentDTO;
import ISA.BloodBank.exception.ResourceConflictException;
import ISA.BloodBank.model.Appointment;
import ISA.BloodBank.model.DonorQuestionnaire;
import ISA.BloodBank.model.RegisteredUser;
import ISA.BloodBank.service.AppointmentService;
import ISA.BloodBank.service.DonorQuestionnaireService;
import ISA.BloodBank.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	private DonorQuestionnaireService donorQuestionnaireService;
	private UserService userService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService,
			DonorQuestionnaireService donorQuestionnaireService, UserService userService) {
		super();
		this.appointmentService = appointmentService;
		this.donorQuestionnaireService = donorQuestionnaireService;
		this.userService = userService;
	}

	@PreAuthorize("hasRole('ROLE_CENTER_ADMINISTRATOR')")
	@PostMapping(value = "/createPredefinedAppointment")
	public ResponseEntity<?> createPredefinedAppointment(@RequestBody AppointmentDTO appointmentDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			return new ResponseEntity<>(appointmentService.createPredefinedAppointment(appointmentDTO),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ROLE_REGISTERED_USER')")
	@PostMapping(value = "/createAppointmentRegisteredUser")
	public ResponseEntity<?> createAppointmentRegisteredUser(
			@RequestBody AppointmentRegisteredUserDTO appointmentRegisteredUserDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			DonorQuestionnaire donorQuestionnaire = donorQuestionnaireService
					.getQuestionnareByUserId(Long.parseLong(appointmentRegisteredUserDTO.getRegisteredUserID()));
			if (donorQuestionnaire.getQuestionnaireId() != null) {
				if (donorQuestionnaire.getRecentlyDonatedBlood() == false) {
					return new ResponseEntity<>(
							appointmentService.createAppointmentRegisteredUser(appointmentRegisteredUserDTO),
							HttpStatus.CREATED);
				} else {
					throw new ResourceConflictException(appointmentRegisteredUserDTO.getRegisteredUserID(),
							"Six months have not passed since the last blood donation!");
				}
			} else {
				throw new ResourceConflictException(appointmentRegisteredUserDTO.getRegisteredUserID(),
						"Please fill the donor questionnaire");
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ROLE_REGISTERED_USER')")
	@PutMapping(value = "/schedulePredefinedAppointment/{appointmentId}/{registeredUserId}")
	public ResponseEntity<?> schedulePredefinedAppointment(@PathVariable Long appointmentId,
			@PathVariable Long registeredUserId, UriComponentsBuilder uriComponentsBuilder) {
		try {
			DonorQuestionnaire donorQuestionnaire = donorQuestionnaireService.getQuestionnareByUserId(registeredUserId);
			Appointment schedulingAppointment = appointmentService.findById(appointmentId);
			RegisteredUser registeredUser = (RegisteredUser) userService.findById(registeredUserId);
			// userService.checkDateToClearPenalties(registeredUserId);
			if (registeredUser.getPenalties() == 3) {
				throw new ResourceConflictException(appointmentId.toString(),
						"Can not schedule appointment, you have 3 penalties!");
			} else if (donorQuestionnaire.getQuestionnaireId() != null) {
				if (donorQuestionnaire.getRecentlyDonatedBlood() == false) {
					if (schedulingAppointment.getIsCancelled()
							&& schedulingAppointment.getRegisteredUser().getUserId() == registeredUserId) {
						throw new ResourceConflictException(appointmentId.toString(),
								"Can not schedule appointment that you have cancelled.");
					} else {
						return new ResponseEntity<>(
								appointmentService.schedulePredefinedAppointment(appointmentId, registeredUserId),
								HttpStatus.CREATED);
					}
				} else {
					throw new ResourceConflictException(registeredUserId.toString(),
							"Six months have not passed since the last blood donation!");
				}
			} else {
				throw new ResourceConflictException(registeredUserId.toString(), "Please fill the donor questionnaire");

			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('ROLE_REGISTERED_USER')")
	@GetMapping(value = "/findPredefinedAppointmentsForMedicalCenter/{id}")
	public ResponseEntity<List<PredefinedAppointmentDTO>> findPredefinedAppointmentsForMedicalCenter(
			@PathVariable Long id) {
		try {
			List<PredefinedAppointmentDTO> predefinedAppointmentDTOs = new ArrayList<PredefinedAppointmentDTO>();
			List<Appointment> appointmentsForCenter = appointmentService.findAllByCenterId(id);
			for (Appointment a : appointmentsForCenter) {
				if (a.getIsAvailable()) {
					PredefinedAppointmentDTO predefinedAppointmentDTO = new PredefinedAppointmentDTO();
					predefinedAppointmentDTO.setAppointmentId(a.getAppointmentId());
					predefinedAppointmentDTO.setDate(a.getDate().toLocalDate().toString());
					predefinedAppointmentDTO.setTime(a.getDate().toLocalTime().toString());
					predefinedAppointmentDTO.setDuration(a.getDuration());
					predefinedAppointmentDTOs.add(predefinedAppointmentDTO);
				}
			}
			return new ResponseEntity<List<PredefinedAppointmentDTO>>(predefinedAppointmentDTOs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_REGISTERED_USER')")
	@GetMapping(value = "/findScheduledAppointmentsForRegisteredUser/{id}")
	public ResponseEntity<List<ScheduledAppointmentDTO>> findScheduledAppointmentsForRegisteredUser(
			@PathVariable Long id) {
		try {
			List<ScheduledAppointmentDTO> scheduledAppointmentDTOs = new ArrayList<ScheduledAppointmentDTO>();
			List<Appointment> appointmentsForUser = appointmentService.findAllByRegisteredUserId(id);
			for (Appointment a : appointmentsForUser) {
				if (a.getIsAvailable() == false && a.getDate().compareTo(LocalDateTime.now()) > 0) {
					ScheduledAppointmentDTO scheduledAppointmentDTO = new ScheduledAppointmentDTO();
					scheduledAppointmentDTO.setAppointmentId(a.getAppointmentId());
					scheduledAppointmentDTO.setRegisteredUserId(id);
					scheduledAppointmentDTO.setDate(a.getDate().toLocalDate().toString());
					scheduledAppointmentDTO.setTime(a.getDate().toLocalTime().toString());
					scheduledAppointmentDTO.setDuration(a.getDuration());
					scheduledAppointmentDTO.setMedicalCenterName(a.getMedicalCenter().getName());
					scheduledAppointmentDTOs.add(scheduledAppointmentDTO);
				}
			}
			return new ResponseEntity<List<ScheduledAppointmentDTO>>(scheduledAppointmentDTOs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_REGISTERED_USER')")
	@PutMapping(value = "/cancelScheduledAppointment/{appointmentId}")
	public ResponseEntity<?> cancelScheduledAppointment(@PathVariable Long appointmentId,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			Appointment cancelingAppointment = appointmentService.findById(appointmentId);
			if (cancelingAppointment != null) {
				if (cancelingAppointment.getDate().compareTo(LocalDateTime.now().plusHours(24)) > 0) {
					return new ResponseEntity<>(appointmentService.cancelScheduledAppointment(appointmentId),
							HttpStatus.CREATED);
				} else {
					throw new ResourceConflictException(appointmentId.toString(),
							"Can not cancel the appointment! There are less than 24 hours left until the appointment.");
				}
			} else {
				throw new ResourceConflictException(appointmentId.toString(), "Appointment does not exist.");
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
