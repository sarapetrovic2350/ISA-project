package ISA.BloodBank.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class CenterAdministrator extends User {

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "medicalCenterId", referencedColumnName = "centerId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MedicalCenter medicalCenter;

	public CenterAdministrator() {
		super();
	}

	public MedicalCenter getMedicalCenter() {
		return medicalCenter;
	}

	public void setMedicalCenter(MedicalCenter medicalCenter) {
		this.medicalCenter = medicalCenter;
	}

	
	

}
