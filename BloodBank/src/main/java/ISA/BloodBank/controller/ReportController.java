package ISA.BloodBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.dto.AddingBloodDTO;
import ISA.BloodBank.dto.CreateReportDTO;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.Report;
import ISA.BloodBank.model.ReportStatus;
import ISA.BloodBank.service.BloodService;
import ISA.BloodBank.service.CenterAdministratorService;
import ISA.BloodBank.service.EquipmentStorageService;
import ISA.BloodBank.service.ReportService;
import ISA.BloodBank.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {
	private ReportService reportService;
	private BloodService bloodService; 
	private UserService userService; 
	private EquipmentStorageService equipmentStorageService; 
	private CenterAdministratorService centerAdministratorService; 

	@Autowired
	public ReportController(ReportService reportCenterService, BloodService bloodService,
			UserService userService, EquipmentStorageService equipmentStorageService, 
			CenterAdministratorService centerAdministratorService) {
		super();
		this.reportService = reportCenterService;
		this.bloodService = bloodService; 
		this.userService = userService; 
		this.equipmentStorageService = equipmentStorageService; 
		this.centerAdministratorService = centerAdministratorService; 
	}
	
	@PostMapping(value = "/createReport")
	public ResponseEntity<?> createReport(@RequestBody CreateReportDTO report,
			UriComponentsBuilder uriComponentsBuilder) {
		report.setReportStatus(ReportStatus.ACCEPTED); 
		try {
			// add validations
			if(report.getPresent() == false) {
				userService.updatePenal(report.getCustomerId()); 
				String ret = "Penalties well refreshed!"; 
				return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
			}
			
			if(report.getReportStatus() == ReportStatus.ACCEPTED) {
				CenterAdministrator admin = centerAdministratorService.findByEmail(report.getAdministratorEmail());
				AddingBloodDTO bloodDto = new AddingBloodDTO(report.getBloodType(), admin.getMedicalCenter().getCenterId(), report.getQuantaty());  
				bloodService.addingBlood(bloodDto); 
				equipmentStorageService.updateQuantaty(admin.getMedicalCenter().getCenterId(), report.getEquipmentQuantaty()); 
			}
			
			return new ResponseEntity<>(reportService.save(report), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Report>> findAll() {
		return new ResponseEntity<List<Report>>(reportService.getAll(), HttpStatus.OK);
	}
}
