package ISA.BloodBank.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class MedicalCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "centerId", unique = true, nullable = false)
	private Long centerId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "averageGrade", nullable = false)
	private Double averageGrade;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	private Address address;
	
	@OneToMany(mappedBy = "medicalCenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CenterAdministrator> centerAdministrators;

	public MedicalCenter() {
	}

	public MedicalCenter(String name, String description, Double averageGrade, Address address,
			Set<CenterAdministrator> centerAdministrators) {
		super();
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.address = address;
		this.centerAdministrators = centerAdministrators;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<CenterAdministrator> getCenterAdministrators() {
		return centerAdministrators;
	}

	public void setCenterAdministrators(Set<CenterAdministrator> centerAdministrators) {
		this.centerAdministrators = centerAdministrators;
	}

	
}
