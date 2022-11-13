package ISA.BloodBank.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ConfirmationToken {

	@Id
	@SequenceGenerator(name = "mySeqGenConfirmationToken", sequenceName = "mySeqConfirmationToken", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenConfirmationToken")
	private Long confirmationTokenId;

	@Column(name = "confirmationToken")
	private String confirmationToken;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private LocalDateTime createdDate;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	public ConfirmationToken() {

	}

	public ConfirmationToken(Long confirmationTokenId, String confirmationToken, LocalDateTime createdDate, User user) {
		super();
		this.confirmationTokenId = confirmationTokenId;
		this.confirmationToken = confirmationToken;
		this.createdDate = createdDate;
		this.user = user;
	}

	public ConfirmationToken(User user) {
		this.user = user;
		createdDate = LocalDateTime.now();
		confirmationToken = UUID.randomUUID().toString();
	}

	public Long getConfirmationTokenId() {
		return confirmationTokenId;
	}

	public void setConfirmationTokenId(Long confirmationTokenId) {
		this.confirmationTokenId = confirmationTokenId;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
