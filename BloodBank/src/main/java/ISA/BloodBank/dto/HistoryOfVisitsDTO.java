package ISA.BloodBank.dto;

public class HistoryOfVisitsDTO {
	private Long reportId;
	private String date;
	private String bloodType;
	private String donatedBloodQuantity;
	private String medicalCenterName;
	
	
	public HistoryOfVisitsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoryOfVisitsDTO(Long reportId, String date, String bloodType, String donatedBloodQuantity,
			String medicalCenterName) {
		super();
		this.reportId = reportId;
		this.date = date;
		this.bloodType = bloodType;
		this.donatedBloodQuantity = donatedBloodQuantity;
		this.medicalCenterName = medicalCenterName;
	}


	public Long getReportId() {
		return reportId;
	}


	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getBloodType() {
		return bloodType;
	}


	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}


	public String getDonatedBloodQuantity() {
		return donatedBloodQuantity;
	}


	public void setDonatedBloodQuantity(String donatedBloodQuantity) {
		this.donatedBloodQuantity = donatedBloodQuantity;
	}


	public String getMedicalCenterName() {
		return medicalCenterName;
	}


	public void setMedicalCenterName(String medicalCenterName) {
		this.medicalCenterName = medicalCenterName;
	}

	
	
}
