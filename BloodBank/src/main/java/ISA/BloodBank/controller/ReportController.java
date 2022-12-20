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
import ISA.BloodBank.model.Report;
import ISA.BloodBank.model.ReportStatus;
import ISA.BloodBank.service.BloodService;
import ISA.BloodBank.service.ReportService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {
	private ReportService reportService;
	private BloodService bloodService; 

	@Autowired
	public ReportController(ReportService reportCenterService, BloodService bloodService) {
		super();
		this.reportService = reportCenterService;
		this.bloodService = bloodService; 
	}
	
	@PostMapping(value = "/createReport")
	public ResponseEntity<?> createReport(@RequestBody CreateReportDTO report,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			// add validations
			if(report.getReportStatus() == ReportStatus.ACCEPTED) {
				AddingBloodDTO bloodDto = new AddingBloodDTO(report.getBloodId(), report.getQuantaty());  
				bloodService.addingBlood(bloodDto); 
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
