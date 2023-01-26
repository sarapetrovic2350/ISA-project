package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.CreateReportDTO;
import ISA.BloodBank.model.Report;

@Service
public interface IReportService {
	
	Report save(CreateReportDTO report); 
	List<Report> getAll();
	List<Report> getAllReportsByMedicalCenterId(Long id); 
}
