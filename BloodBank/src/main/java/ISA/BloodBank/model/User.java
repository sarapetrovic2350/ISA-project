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

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	private Long userId;
	
	@Column(name = "jmbg",  unique = true, nullable = false)
	private Long jmbg;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	private Occupation occupation;
	
	@Column(name = "occupationInfo", nullable = false)
	private String occupationInfo;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "authorityId", referencedColumnName = "authorityId")
	private Authority authority;

	public User() {
	}

	public User(Long jmbg, String name, String surname, String password, Gender gender, Occupation occupation,
			String occupationInfo, String email, String phoneNumber, Address address, UserType userType) {
		super();
		this.jmbg = jmbg;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.gender = gender;
		this.occupation = occupation;
		this.occupationInfo = occupationInfo;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getJmbg() {
		return jmbg;
	}

	public void setJmbg(Long jmbg) {
		this.jmbg = jmbg;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public String getOccupationInfo() {
		return occupationInfo;
	}

	public void setOccupationInfo(String occupationInfo) {
		this.occupationInfo = occupationInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
