package ISA.BloodBank.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import ISA.BloodBank.model.BloodType;
import ISA.BloodBank.model.ReportStatus;

public class CreateReportDTO {

	@NotBlank
	private String administratorEmail;

	@NotBlank
	private Long customerId;

	@NotBlank
	private Long bloodId;

	@NotBlank
	private Long appointmentId;

	@NotBlank
	private Double haemoglobinValue;

	@NotBlank
	private String heart;

	@NotBlank
	private String lungs;

	@NotBlank
	private Double weight;

	@NotBlank
	private Double height;

	@NotBlank
	private Double bloodPreasure;

	@NotBlank
	private ReportStatus reportStatus;

	@NotBlank
	private Double quantaty;

	@NotBlank
	private String reason;

	@NotBlank
	private String date;

	@NotBlank
	private Boolean present;

	@NotBlank
	private Double equipmentQuantaty;

	@NotBlank
	private BloodType bloodType;

	CreateReportDTO() {
	}

	public CreateReportDTO(String administratorEmail, Long customerId, Long bloodId, Long appointmentId,
			Double haemoglobinValue, String heart, String lungs, Double weight, Double height, Double bloodPreasure,
			ReportStatus reportStatus, Double quantaty, String reason, String date, Boolean present,
			Double equipmentQuantaty, BloodType bloodType) {
		super();
		this.administratorEmail = administratorEmail;
		this.customerId = customerId;
		this.bloodId = bloodId;
		this.appointmentId = appointmentId;
		this.haemoglobinValue = haemoglobinValue;
		this.heart = heart;
		this.lungs = lungs;
		this.weight = weight;
		this.height = height;
		this.bloodPreasure = bloodPreasure;
		this.reportStatus = reportStatus;
		this.quantaty = quantaty;
		this.reason = reason;
		this.date = date;
		this.present = present;
		this.equipmentQuantaty = equipmentQuantaty;
		this.bloodType = bloodType;
	}

	public String getAdministratorEmail() {
		return administratorEmail;
	}

	public void setAdministratorEmail(String administratorEmail) {
		this.administratorEmail = administratorEmail;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getBloodId() {
		return bloodId;
	}

	public void setBloodId(Long bloodId) {
		this.bloodId = bloodId;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Double getHaemoglobinValue() {
		return haemoglobinValue;
	}

	public void setHaemoglobinValue(Double haemoglobinValue) {
		this.haemoglobinValue = haemoglobinValue;
	}

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public String getLungs() {
		return lungs;
	}

	public void setLungs(String lungs) {
		this.lungs = lungs;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getBloodPreasure() {
		return bloodPreasure;
	}

	public void setBloodPreasure(Double bloodPreasure) {
		this.bloodPreasure = bloodPreasure;
	}

	public ReportStatus getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Double getQuantaty() {
		return quantaty;
	}

	public void setQuantaty(Double quantaty) {
		this.quantaty = quantaty;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Boolean getPresent() {
		return present;
	}

	public void setPresent(Boolean present) {
		this.present = present;
	}

	public Double getEquipmentQuantaty() {
		return equipmentQuantaty;
	}

	public void setEquipmentQuantaty(Double equipmentQuantaty) {
		this.equipmentQuantaty = equipmentQuantaty;
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

}
