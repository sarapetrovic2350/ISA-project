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

import ISA.BloodBank.dto.CreateReportDTO;
import ISA.BloodBank.model.Report;
import ISA.BloodBank.service.ReportService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {
	private ReportService reportCenterService;

	@Autowired
	public ReportController(ReportService reportCenterService) {
		super();
		this.reportCenterService = reportCenterService;
	}
	
	@PostMapping(value = "/createCenter")
	public ResponseEntity<?> createCenter(@RequestBody CreateReportDTO report,
			UriComponentsBuilder uriComponentsBuilder) {
		try {
			// add validations
			return new ResponseEntity<>(reportCenterService.save(report), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Report>> findAll() {
		return new ResponseEntity<List<Report>>(reportCenterService.getAll(), HttpStatus.OK);
	}
}
