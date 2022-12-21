package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.SystemAdministratorRegistrationDTO;
import ISA.BloodBank.model.SystemAdministrator;

@Service
public interface ISystemAdministratorService {
	
	SystemAdministrator registerSystemAdministrator(
			SystemAdministratorRegistrationDTO systemAdministratorRegistrationDTO);

	List<SystemAdministrator> getAllSystemAdministrators();

}
