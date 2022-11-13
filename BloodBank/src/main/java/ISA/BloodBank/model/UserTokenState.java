package ISA.BloodBank.model;

public class UserTokenState {

	private String accessToken;
	private Long expiresIn;
	private User user;

	public UserTokenState() {
		this.expiresIn = null;
		this.accessToken = null;
	}

	public UserTokenState(String accessToken, long expiresIn, User user) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
