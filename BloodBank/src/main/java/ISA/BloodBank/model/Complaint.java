package ISA.BloodBank.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@Entity
@Table(name="Complaint")
public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "complaintId", unique = true, nullable = false)
	private Long complaintId;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "textComplaint", nullable = false)
	private String textComplaint;
	
	@Column(name = "textAnswer", nullable = false)
	private String textAnswer;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private RegisteredUser registeredUser;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "centerAdminId", referencedColumnName = "userId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private CenterAdministrator centerAdministrator;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "medicalCenterId", referencedColumnName = "centerId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MedicalCenter medicalCentar;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "systemAdminId", referencedColumnName = "userId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private SystemAdministrator systemAdministrator;
	
	public Complaint() {}

	public Complaint(Long complaintId, LocalDateTime date, String textComplaint, String textAnswer,
			RegisteredUser registeredUser, CenterAdministrator centerAdministrator, MedicalCenter medicalCentar,
			SystemAdministrator systemAdministrator) {
		super();
		this.complaintId = complaintId;
		this.date = date;
		this.textComplaint = textComplaint;
		this.textAnswer = textAnswer;
		this.registeredUser = registeredUser;
		this.centerAdministrator = centerAdministrator;
		this.medicalCentar = medicalCentar;
		this.systemAdministrator = systemAdministrator;
	}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getTextComplaint() {
		return textComplaint;
	}

	public void setTextComplaint(String textComplaint) {
		this.textComplaint = textComplaint;
	}

	public String getTextAnswer() {
		return textAnswer;
	}

	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public CenterAdministrator getCenterAdministrator() {
		return centerAdministrator;
	}

	public void setCenterAdministrator(CenterAdministrator centerAdministrator) {
		this.centerAdministrator = centerAdministrator;
	}

	public MedicalCenter getMedicalCentar() {
		return medicalCentar;
	}

	public void setMedicalCentar(MedicalCenter medicalCentar) {
		this.medicalCentar = medicalCentar;
	}

	public SystemAdministrator getSystemAdministrator() {
		return systemAdministrator;
	}

	public void setSystemAdministrator(SystemAdministrator systemAdministrator) {
		this.systemAdministrator = systemAdministrator;
	}
	
	
	
}
