package ISA.BloodBank.model;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "medicalCenterId", referencedColumnName = "centerId")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MedicalCenter medicalCenter;
	
	public Blood() {}

	public Blood(Long bloodId, BloodType bloodType, Double quantaty, MedicalCenter medicalCenter) {
		super();
		this.bloodId = bloodId;
		this.bloodType = bloodType;
		this.quantaty = quantaty;
		this.medicalCenter = medicalCenter;
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

	public MedicalCenter getMedicalCenter() {
		return medicalCenter;
	}

	public void setMedicalCenter(MedicalCenter medicalCenter) {
		this.medicalCenter = medicalCenter;
	}

}
