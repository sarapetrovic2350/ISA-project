package ISA.BloodBank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ISA.BloodBank.model.Address;

public class MedicalCenterUpdateDTO {

	@NotNull
	private long centerId;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@NotBlank
	private Double averageGrade;

	@NotBlank
	private Address adress;

	public MedicalCenterUpdateDTO() {
	}

	public MedicalCenterUpdateDTO(long centerId, String name, String description, Double averageGrade, Address adress) {
		super();
		this.centerId = centerId;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.adress = adress;
	}

	public long getCenterId() {
		return centerId;
	}

	public void setCenterId(long centerId) {
		this.centerId = centerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

}
