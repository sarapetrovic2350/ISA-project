package ISA.BloodBank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AppointmentRegisteredUserDTO {

	@NotBlank
	private String date;

	@NotBlank
	private String time;

	@NotNull
	private String medicalCenterID;

	@NotNull
	private String registeredUserID;

	public AppointmentRegisteredUserDTO() {
	}

	public AppointmentRegisteredUserDTO(String date, String time, String medicalCenterID, String registeredUserID) {
		super();
		this.date = date;
		this.time = time;
		this.medicalCenterID = medicalCenterID;
		this.registeredUserID = registeredUserID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMedicalCenterID() {
		return medicalCenterID;
	}

	public void setMedicalCenterID(String medicalCenterID) {
		this.medicalCenterID = medicalCenterID;
	}

	public String getRegisteredUserID() {
		return registeredUserID;
	}

	public void setRegisteredUserID(String registeredUserID) {
		this.registeredUserID = registeredUserID;
	}

}
