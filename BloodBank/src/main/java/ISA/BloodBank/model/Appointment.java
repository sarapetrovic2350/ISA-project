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
@Table(name="Appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointmentId", unique = true, nullable = false)
	private Long appointmentId;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "duration", nullable = false)
	private String duration;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private RegisteredUser registeredUser;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "centerAdminId", referencedColumnName = "userId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private CenterAdministrator centerAdministrator;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "centerId", referencedColumnName = "centerId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MedicalCenter medicalCenter;
	
	@Column(name = "isAvailable", nullable = false)
	private Boolean isAvailable;
	
	public Appointment() {}

	public Appointment(Long appointmentId, LocalDateTime date, String duration, RegisteredUser registeredUser,
			CenterAdministrator centerAdministrator, MedicalCenter medicalCenter,Boolean isAvailable) {
		super();
		this.appointmentId = appointmentId;
		this.date = date;
		this.duration = duration;
		this.registeredUser = registeredUser;
		this.centerAdministrator = centerAdministrator;
		this.medicalCenter = medicalCenter;
		this.isAvailable = isAvailable;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public MedicalCenter getMedicalCenter() {
		return medicalCenter;
	}

	public void setMedicalCenter(MedicalCenter medicalCenter) {
		this.medicalCenter = medicalCenter;
	}
	
	
	
	
}
