package ISA.BloodBank.iservice;

import org.springframework.stereotype.Service;

import ISA.BloodBank.model.ConfirmationToken;
import ISA.BloodBank.model.User;

@Service
public interface IConfirmationTokenService {
	ConfirmationToken findByConfirmationToken(String confirmationToken);

	ConfirmationToken saveConfirmationToken(User user);
}
