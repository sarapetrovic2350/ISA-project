package ISA.BloodBank.dto;

import javax.validation.constraints.NotBlank;

public class DonorQuestionnaireDTO {

	@NotBlank
	private String userEmail;

	@NotBlank
	private Integer weight;

	@NotBlank
	private Integer age;

	@NotBlank
	private Boolean generalGoodHealth;

	@NotBlank
	private Boolean symptomsOfIllness;

	@NotBlank
	private Boolean underMedication;

	@NotBlank
	private Boolean normalBloodPressure;

	@NotBlank
	private Boolean skinDisorders;

	@NotBlank
	private Boolean tattooOrPiercing;

	@NotBlank
	private Boolean recentlyVisitedDentist;

	@NotBlank
	private Boolean recentlyDonatedBlood;

	@NotBlank
	private Boolean hasPeriod;

	public DonorQuestionnaireDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonorQuestionnaireDTO(String userEmail, Integer weight, Integer age, Boolean generalGoodHealth,
			Boolean symptomsOfIllness, Boolean underMedication, Boolean normalBloodPressure, Boolean skinDisorders,
			Boolean tattooOrPiercing, Boolean recentlyVisitedDentist, Boolean recentlyDonatedBlood, Boolean hasPeriod) {
		super();
		this.userEmail = userEmail;
		this.weight = weight;
		this.age = age;
		this.generalGoodHealth = generalGoodHealth;
		this.symptomsOfIllness = symptomsOfIllness;
		this.underMedication = underMedication;
		this.normalBloodPressure = normalBloodPressure;
		this.skinDisorders = skinDisorders;
		this.tattooOrPiercing = tattooOrPiercing;
		this.recentlyVisitedDentist = recentlyVisitedDentist;
		this.recentlyDonatedBlood = recentlyDonatedBlood;
		this.hasPeriod = hasPeriod;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getGeneralGoodHealth() {
		return generalGoodHealth;
	}

	public void setGeneralGoodHealth(Boolean generalGoodHealth) {
		this.generalGoodHealth = generalGoodHealth;
	}

	public Boolean getSymptomsOfIllness() {
		return symptomsOfIllness;
	}

	public void setSymptomsOfIllness(Boolean symptomsOfIllness) {
		this.symptomsOfIllness = symptomsOfIllness;
	}

	public Boolean getUnderMedication() {
		return underMedication;
	}

	public void setUnderMedication(Boolean underMedication) {
		this.underMedication = underMedication;
	}

	public Boolean getNormalBloodPressure() {
		return normalBloodPressure;
	}

	public void setNormalBloodPressure(Boolean normalBloodPressure) {
		this.normalBloodPressure = normalBloodPressure;
	}

	public Boolean getSkinDisorders() {
		return skinDisorders;
	}

	public void setSkinDisorders(Boolean skinDisorders) {
		this.skinDisorders = skinDisorders;
	}

	public Boolean getTattooOrPiercing() {
		return tattooOrPiercing;
	}

	public void setTattooOrPiercing(Boolean tattooOrPiercing) {
		this.tattooOrPiercing = tattooOrPiercing;
	}

	public Boolean getRecentlyVisitedDentist() {
		return recentlyVisitedDentist;
	}

	public void setRecentlyVisitedDentist(Boolean recentlyVisitedDentist) {
		this.recentlyVisitedDentist = recentlyVisitedDentist;
	}

	public Boolean getRecentlyDonatedBlood() {
		return recentlyDonatedBlood;
	}

	public void setRecentlyDonatedBlood(Boolean recentlyDonatedBlood) {
		this.recentlyDonatedBlood = recentlyDonatedBlood;
	}

	public Boolean getHasPeriod() {
		return hasPeriod;
	}

	public void setHasPeriod(Boolean hasPeriod) {
		this.hasPeriod = hasPeriod;
	}

}
