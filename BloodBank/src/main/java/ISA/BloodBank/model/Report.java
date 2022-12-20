package ISA.BloodBank.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long reportId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private RegisteredUser registeredUser;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "centerAdminId", referencedColumnName = "userId")
	private CenterAdministrator centerAdministrator;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "bloodId", referencedColumnName = "bloodId")
	private Blood blood;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "appointmentId", referencedColumnName = "appointmentId")
	private Appointment appointment;
	
	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "haemoglobinValue", nullable = true)
	private Double haemoglobinValue;
	
	@Column(name = "heart", nullable = true)
	private String heart;
	
	@Column(name = "lungs", nullable = true)
	private String lungs;
	
	@Column(name = "weight", nullable = true)
	private Double weight;
	
	@Column(name = "height", nullable = true)
	private Double height;
	
	@Column(name = "bloodPreasure", nullable = true)
	private Double bloodPreasure;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ReportStatus reportStatus;
	
	@Column(name = "quantaty", nullable = true)
	private Double quantaty;
	
	@Column(name = "reason", nullable = true)
	private String reasonForDenying;
	
	public Report(){}

	public Report(Long reportId, RegisteredUser registeredUser, CenterAdministrator centerAdministrator, Blood blood,
			Appointment appointment, LocalDateTime date, Double haemoglobinValue, String heart, String lungs,
			Double weight, Double height, Double bloodPreasure, ReportStatus reportStatus, Double quantaty,
			String reasonForDenying) {
		super();
		this.reportId = reportId;
		this.registeredUser = registeredUser;
		this.centerAdministrator = centerAdministrator;
		this.blood = blood;
		this.appointment = appointment;
		this.date = date;
		this.haemoglobinValue = haemoglobinValue;
		this.heart = heart;
		this.lungs = lungs;
		this.weight = weight;
		this.height = height;
		this.bloodPreasure = bloodPreasure;
		this.reportStatus = reportStatus;
		this.quantaty = quantaty;
		this.reasonForDenying = reasonForDenying;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
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

	public Blood getBlood() {
		return blood;
	}

	public void setBlood(Blood blood) {
		this.blood = blood;
	}

	public Double getHaemoglobinValue() {
		return haemoglobinValue;
	}

	public void setHaemoglobinValue(Double haemoglobinValue) {
		this.haemoglobinValue = haemoglobinValue;
	}

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public String getLungs() {
		return lungs;
	}

	public void setLungs(String lungs) {
		this.lungs = lungs;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getBloodPreasure() {
		return bloodPreasure;
	}

	public void setBloodPreasure(Double bloodPreasure) {
		this.bloodPreasure = bloodPreasure;
	}

	public ReportStatus getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Double getQuantaty() {
		return quantaty;
	}

	public void setQuantaty(Double quantaty) {
		this.quantaty = quantaty;
	}

	public String getReasonForDenying() {
		return reasonForDenying;
	}

	public void setReasonForDenying(String reasonForDenying) {
		this.reasonForDenying = reasonForDenying;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
