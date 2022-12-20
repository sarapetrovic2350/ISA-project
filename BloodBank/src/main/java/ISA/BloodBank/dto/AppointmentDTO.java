package ISA.BloodBank.dto;

import java.time.LocalDateTime;

import ISA.BloodBank.model.Address;
import ISA.BloodBank.model.Gender;
import ISA.BloodBank.model.Occupation;

public class AppointmentDTO {

	private String duration;

	private String date;
	
	private String time;
	
	private String administratorCenterID;
	
	public AppointmentDTO() {}

	public AppointmentDTO(String duration, String date, String time,String administratorCenterID) {
		super();
		this.duration = duration;
		this.date = date;
		this.time = time;
		this.administratorCenterID = administratorCenterID;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAdministratorCenterID() {
		return administratorCenterID;
	}

	public void setAdministratorCenterID(String administratorCenterID) {
		this.administratorCenterID = administratorCenterID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

	
}
