package ISA.BloodBank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ISA.BloodBank.model.BloodType;

public class AddingBloodDTO {

	@NotBlank
	private BloodType bloodType;

	@NotNull
	private Long centerId;

	@NotBlank
	private Double quantaty;

	public AddingBloodDTO() {
	}

	public AddingBloodDTO(BloodType bloodType, Long centerId, Double quantaty) {
		super();
		this.bloodType = bloodType;
		this.centerId = centerId;
		this.quantaty = quantaty;
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

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}

}
