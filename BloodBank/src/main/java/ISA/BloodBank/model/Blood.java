package ISA.BloodBank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Bloods")
public class Blood {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bloodId", unique = true, nullable = false)
	private Long bloodId;

	@Enumerated(EnumType.STRING)
	@Column(name = "bloodType", nullable = false)
	private BloodType bloodType;


	@Column(name = "quantaty")
	private Double quantaty;
	
	public Blood() {}

	public Blood(Long bloodId, BloodType bloodType, Double quantaty) {
		super();
		this.bloodId = bloodId;
		this.bloodType = bloodType;
		this.quantaty = quantaty;
	}

	public Long getBloodId() {
		return bloodId;
	}

	public void setBloodId(Long bloodId) {
		this.bloodId = bloodId;
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public Double getQuantaty() {
		return quantaty;
	}

	public void setQuantaty(Double quantaty) {
		this.quantaty = quantaty;
	}
	
	

}
