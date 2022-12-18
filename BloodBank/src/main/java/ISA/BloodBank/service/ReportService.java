package ISA.BloodBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.CreateReportDTO;
import ISA.BloodBank.iservice.IReportService;
import ISA.BloodBank.model.Report;
import ISA.BloodBank.repository.IBloodRepository;
import ISA.BloodBank.repository.ICenterAdministratorRepository;
import ISA.BloodBank.repository.IReportRepository;
import ISA.BloodBank.repository.IUserRepository;

@Service
public class ReportService implements IReportService{

	private IReportRepository reportRepository; 
	private IUserRepository userRepository; 
	private ICenterAdministratorRepository centerAdministratorRepository; 
	private IBloodRepository bloodRepository; 
	
	@Autowired
	public ReportService(IReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	@Override
	public Report save(CreateReportDTO report) {		
		return null;
	}

	@Override
	public List<Report> getAll() {
		return reportRepository.findAll();
	}
	
	
}
