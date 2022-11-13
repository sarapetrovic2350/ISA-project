package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.CenterAdministratorRegistrationDTO;
import ISA.BloodBank.model.CenterAdministrator;

@Service
public interface ICenterAdministratorService {

	CenterAdministrator registerCenterAdministrator(
			CenterAdministratorRegistrationDTO centerAdministratorRegistrationDTO);

	List<CenterAdministrator> getAllCenterAdministrator();
}
