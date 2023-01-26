package ISA.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.iservice.IConfirmationTokenService;
import ISA.BloodBank.model.ConfirmationToken;
import ISA.BloodBank.model.User;
import ISA.BloodBank.repository.IConfirmationTokenRepository;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService {

	private final IConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	public ConfirmationTokenService(IConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}
	
	@Override
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	}

	@Override
	public ConfirmationToken saveConfirmationToken(User user) {
		return confirmationTokenRepository.save(new ConfirmationToken(user));
	}

}
