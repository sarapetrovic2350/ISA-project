package ISA.BloodBank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<MedicalCenter>> findAll() {
		return new ResponseEntity<List<MedicalCenter>>(medicalCenterService.getAll(), HttpStatus.OK);
	}
		
	@GetMapping("/findAll")
	  public ResponseEntity<Map<String, Object>> findAllPagination(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "3") int size
	      ) {
	    try {      
	      List<MedicalCenter> centers = new ArrayList<MedicalCenter>();
	      Pageable paging = PageRequest.of(page, size);
	      
	      Page<MedicalCenter> pageCenters = medicalCenterService.findAll(paging);
	      centers = pageCenters.getContent();
	            
	      Map<String, Object> response = new HashMap<>();
	      response.put("centers", centers);
	      response.put("currentPage", pageCenters.getNumber());
	      response.put("totalItems", pageCenters.getTotalElements());
	      response.put("totalPages", pageCenters.getTotalPages());
	      
	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 @RequestMapping(value="/updateCenter", method = RequestMethod.PUT)
	 public @ResponseBody MedicalCenter update(@RequestBody MedicalCenter medCenterDto) { 
		 System.out.println(medCenterDto); 
		 return medicalCenterService.update(medCenterDto);
		 
	 }
	 
	 @GetMapping(value="/getMedicalCenterById/{centerId}")
	 public MedicalCenter loadById(@PathVariable Long centerId) {
		return this.medicalCenterService.findById(centerId);
	 }
	 
	 @GetMapping(value = "/searchMedicalCenterByNameAndPlace/{name}/{place}")
		public ResponseEntity<Map<String, Object>> getAllMedicalCentersForSearch(@PathVariable String name,@PathVariable String place){
			List<MedicalCenter> searchedCenters = medicalCenterService.findMedicalCenterByNameAndPlace(name, place);
			int total = searchedCenters.size();
			Map<String, Object> response = new HashMap<>();
			response.put("searchedCenters", searchedCenters);
			response.put("totalItems", total);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 
	 @GetMapping(value = "/filterMedicalCenter/{name}/{place}/{grade}")
		public ResponseEntity<Map<String, Object>> getFilteredMedicalCenters(@PathVariable String name,@PathVariable String place, @PathVariable String grade){
			List<MedicalCenter> filteredCenters = medicalCenterService.filterMedicalCenter(name, place, grade);
			int total = filteredCenters.size();
			Map<String, Object> response = new HashMap<>();
			response.put("filteredCenters", filteredCenters);
			response.put("totalItems", total);
			
			return new ResponseEntity<>(response, HttpStatus.OK);	
		 
		}
}
