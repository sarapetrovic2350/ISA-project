package ISA.BloodBank.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RegisteredUser extends User {
	
	private static final long serialVersionUID = 8416309346478992571L;
	
	@Column(name = "penalties")
	private Integer penalties = 0;

	public RegisteredUser() {
		super();
	}

	public Integer getPenalties() {
		return penalties;
	}

	public void setPenalties(Integer penalties) {
		this.penalties = penalties;
	}

}
