package ISA.BloodBank.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class CenterAdministrator extends User {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "medicalCenterId", referencedColumnName = "centerId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MedicalCenter medicalCenter;
	
	@Column(name = "firstLoginChangePassword")
	private Boolean firstLoginChangePassword;

	public CenterAdministrator() {
		super();
	}
	
	public Boolean getFirstLoginChangePassword() {
		return firstLoginChangePassword;
	}

	public void setFirstLoginChangePassword(Boolean firstLoginChangePassword) {
		this.firstLoginChangePassword = firstLoginChangePassword;
	}

	public MedicalCenter getMedicalCenter() {
		return medicalCenter;
	}

	public void setMedicalCenter(MedicalCenter medicalCenter) {
		this.medicalCenter = medicalCenter;
	}

	
	

}
