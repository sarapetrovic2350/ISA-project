package ISA.BloodBank.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RegisteredUser extends User {

	@Column(name = "penalties")
    private Integer penalties = 0;

	public RegisteredUser() {
		super();
	}
	
	
}
