package ISA.BloodBank.dto;

public class AddingBloodDTO {
	
	private Long bloodId; 
	private Double quantaty; 
	
	public AddingBloodDTO() {}

	public AddingBloodDTO(Long bloodId, Double quantaty) {
		super();
		this.bloodId = bloodId;
		this.quantaty = quantaty;
	}

	public Long getBloodId() {
		return bloodId;
	}

	public void setBloodId(Long bloodId) {
		this.bloodId = bloodId;
	}

	public Double getQuantaty() {
		return quantaty;
	}

	public void setQuantaty(Double quantaty) {
		this.quantaty = quantaty;
	}
	
	
}
