package ISA.BloodBank.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CenterAdministrator extends User {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "medicalCenterId", referencedColumnName = "centerId")
	private MedicalCenter medicalCenter;

	public CenterAdministrator() {
		super();
	}

}
