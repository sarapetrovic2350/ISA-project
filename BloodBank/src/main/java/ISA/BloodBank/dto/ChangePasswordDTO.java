package ISA.BloodBank.dto;

import javax.validation.constraints.NotBlank;

public class ChangePasswordDTO {

	@NotBlank
	private String email;

	@NotBlank
	private String oldPassword;

	@NotBlank
	private String password;

	@NotBlank
	private String passwordRepeated;

	public ChangePasswordDTO() {
	}

	public ChangePasswordDTO(String email, String oldPassword, String password, String passwordRepeated) {
		super();
		this.email = email;
		this.oldPassword = oldPassword;
		this.password = password;
		this.passwordRepeated = passwordRepeated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeated() {
		return passwordRepeated;
	}

	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}

}
