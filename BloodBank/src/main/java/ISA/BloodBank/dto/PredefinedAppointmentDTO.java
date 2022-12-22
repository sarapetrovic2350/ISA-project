package ISA.BloodBank.dto;

public class PredefinedAppointmentDTO {
	
	private Long appointmentId;
	private String date;
	private String time;
	private String duration;
	
	public PredefinedAppointmentDTO() {}
	
	public PredefinedAppointmentDTO(Long appointmentId, String date, String time, String duration) {
		super();
		this.appointmentId = appointmentId;
		this.date = date;
		this.time = time;
		this.duration = duration;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
}
