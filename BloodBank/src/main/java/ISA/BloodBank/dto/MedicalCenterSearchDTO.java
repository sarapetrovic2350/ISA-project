package ISA.BloodBank.dto;

import javax.validation.constraints.NotBlank;

import ISA.BloodBank.model.Address;
import ISA.BloodBank.model.MedicalCenter;

public class MedicalCenterSearchDTO {

	@NotBlank
	private String name;

	@NotBlank
	private Double averageGrade;

	@NotBlank
	private Address address;

	public MedicalCenterSearchDTO() {
	}

	public MedicalCenterSearchDTO(String name, Double averageGrade, Address address) {
		super();
		this.name = name;
		this.averageGrade = averageGrade;
		this.address = address;
	}

	public MedicalCenterSearchDTO(MedicalCenter medicalCenter) {
		name = medicalCenter.getName();
		averageGrade = medicalCenter.getAverageGrade();
		address = medicalCenter.getAddress();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public Address getAdress() {
		return address;
	}

	public void setAdress(Address address) {
		this.address = address;
	}

}
