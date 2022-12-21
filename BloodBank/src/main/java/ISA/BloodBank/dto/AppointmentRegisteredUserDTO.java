package ISA.BloodBank.dto;

public class AppointmentRegisteredUserDTO {
	
	private String duration;

	private String date;
	
	private String time;
	
	private String administratorCenterID;
	
	private String registeredUserID;
	
	public AppointmentRegisteredUserDTO() {}

	public AppointmentRegisteredUserDTO(String duration, String date, String time, String administratorCenterID,
			String registeredUserID) {
		super();
		this.duration = duration;
		this.date = date;
		this.time = time;
		this.administratorCenterID = administratorCenterID;
		this.registeredUserID = registeredUserID;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAdministratorCenterID() {
		return administratorCenterID;
	}

	public void setAdministratorCenterID(String administratorCenterID) {
		this.administratorCenterID = administratorCenterID;
	}

	public String getRegisteredUserID() {
		return registeredUserID;
	}

	public void setRegisteredUserID(String registeredUserID) {
		this.registeredUserID = registeredUserID;
	}
	
	

}
