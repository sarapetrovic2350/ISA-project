package ISA.BloodBank.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.CreateReportDTO;
import ISA.BloodBank.iservice.IAppointmentService;
import ISA.BloodBank.iservice.IReportService;
import ISA.BloodBank.model.Appointment;
import ISA.BloodBank.model.Blood;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.RegisteredUser;
import ISA.BloodBank.model.Report;
import ISA.BloodBank.model.ReportStatus;
import ISA.BloodBank.repository.IAppointmentRepository;
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
	private IAppointmentRepository appointmentsRepository; 
	
	@Autowired
	public ReportService(IReportRepository reportRepository, IUserRepository userRepository, ICenterAdministratorRepository centerAdministratorRepository, 
			IBloodRepository bloodRepository, IAppointmentRepository appointmentsRepository) {
		this.reportRepository = reportRepository;
		this.userRepository = userRepository; 
		this.centerAdministratorRepository = centerAdministratorRepository; 
		this.bloodRepository = bloodRepository; 
		this.appointmentsRepository = appointmentsRepository; 
	}

	@Override
	public Report save(CreateReportDTO report) {
		
		Report rep= new Report(); 
		
		System.out.println(report.getAdministratorEmail());
		System.out.println(report.getCustomerId());
		
		CenterAdministrator centAdmi = (CenterAdministrator) centerAdministratorRepository.findByEmail(report.getAdministratorEmail());
		rep.setCenterAdministrator(centAdmi); 
		RegisteredUser user = (RegisteredUser) userRepository.findByUserId(report.getCustomerId()); 
		rep.setRegisteredUser(user); 
//		Blood blood = bloodRepository.findById(report.getBloodId()).get(); 
//		rep.setBlood(blood); 
		Appointment appointment = (Appointment) appointmentsRepository.findById(report.getAppointmentId()).get(); 
		rep.setAppointment(appointment); 
		
		rep.setHaemoglobinValue(report.getHaemoglobinValue());
		rep.setHeart(report.getHeart()); 
		rep.setLungs(report.getLungs()); 
		rep.setWeight(report.getWeight()); 
		rep.setHeight(report.getHeight()); 
		//rep.setBloodPreasure(report.getBloodPreasure());
		rep.setBloodPreasure(90.0); 
		rep.setReportStatus(report.getReportStatus());  
		
		if(report.getReportStatus() == ReportStatus.ACCEPTED ) {	
			Blood blood = bloodRepository.findById(report.getBloodId()).get(); 
			rep.setBlood(blood); 
			rep.setQuantaty(report.getQuantaty());
			rep.setReasonForDenying("Krv je primljena"); 
		}else {
			rep.setReasonForDenying(report.getReason());
		} 
		LocalDateTime lt = LocalDateTime.now();
		rep.setDate(lt); 	
		
		System.out.println(rep.getCenterAdministrator());
		System.out.println(rep.getQuantaty());
		
		return reportRepository.save(rep);
	}

	@Override
	public List<Report> getAll() {
		return reportRepository.findAll();
	}
	
	
}
