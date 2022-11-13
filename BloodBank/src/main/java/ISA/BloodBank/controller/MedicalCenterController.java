package ISA.BloodBank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ISA.BloodBank.dto.MedicalCenterSearchDTO;
import ISA.BloodBank.dto.MedicalCenterUpdateDTO;
import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.model.User;
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
	
	 @RequestMapping(value="/updateCenter", method = RequestMethod.PUT)
	 public @ResponseBody MedicalCenter update(@RequestBody MedicalCenter medCenterDto) {
		 System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); 
		 System.out.println(medCenterDto); 
		 return medicalCenterService.update(medCenterDto);

		 //return null;
	 }
	 
	 @GetMapping(value="/getMedicalCenterById/{centerId}")
	 public MedicalCenter loadById(@PathVariable Long centerId) {
		return this.medicalCenterService.findById(centerId);
	 }
	 
	 @GetMapping(value = "/searchMedicalCenterByNameAndPlace/{name}/{place}")
		public ResponseEntity<List<MedicalCenterSearchDTO>> getAllMedicalCentersForSearch(@PathVariable String name,@PathVariable String place){
			List<MedicalCenterSearchDTO> medicalCentersDTO = new ArrayList<MedicalCenterSearchDTO>();
			List<MedicalCenter> medicalCenters = medicalCenterService.findMedicalCenterByNameAndPlace(name, place);
			for (MedicalCenter medicalCenter : medicalCenters) {
				medicalCentersDTO.add(new MedicalCenterSearchDTO(medicalCenter));
			}
			return medicalCentersDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(medicalCentersDTO);
		}
}
