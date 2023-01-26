package ISA.BloodBank.dto;

public class AppointmentsShowDTO {
	
	private String date;
	private String time;
	private String status; 
	private Long userId; 
	private String userName; 
	private String userSurname; 
	
	public AppointmentsShowDTO() {}

	public AppointmentsShowDTO(String date, String time, String status, Long userId, String userName,
			String userSurname) {
		super();
		this.date = date;
		this.time = time;
		this.status = status;
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	
}
