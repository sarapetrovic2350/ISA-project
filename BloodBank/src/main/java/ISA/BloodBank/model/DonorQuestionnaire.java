package ISA.BloodBank.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DonorQuestionnaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long questionnaireId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private RegisteredUser registeredUser;
	
	@Column(name = "weight", nullable = false)
	private Integer weight;
	
	@Column(name = "age", nullable = false)
	private Integer age;
	
	@Column(name = "generalGoodHealth", nullable = false)
	private Boolean generalGoodHealth;
	
	@Column(name = "symptomsOfIllness", nullable = false)
	private Boolean symptomsOfIllness;
	
	@Column(name = "underMedication", nullable = false)
	private Boolean underMedication;
	
	@Column(name = "normalBloodPressure", nullable = false)
	private Boolean normalBloodPressure;
	
	@Column(name = "skinDisorders", nullable = false)
	private Boolean skinDisorders;
	
	@Column(name = "tattooOrPiercing", nullable = false)
	private Boolean tattooOrPiercing;
	
	@Column(name = "visitedDentist", nullable = false)
	private Boolean recentlyVisitedDentist;
	
	@Column(name = "recentlyDonatedBlood", nullable = false)
	private Boolean recentlyDonatedBlood;
	
	@Column(name = "hasPeriod", nullable = false)
	private Boolean hasPeriod;

	
	public DonorQuestionnaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonorQuestionnaire(RegisteredUser registeredUser, Integer weight, Integer age, Boolean generalGoodHealth,
			Boolean symptomsOfIllness, Boolean underMedication, Boolean normalBloodPressure, Boolean skinDisorders,
			Boolean tattooOrPiercing, Boolean recentlyVisitedDentist, Boolean recentlyDonatedBlood, Boolean hasPeriod) {
		super();
		this.registeredUser = registeredUser;
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

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
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
