package ISA.BloodBank.iservice;

import org.springframework.stereotype.Service;

import ISA.BloodBank.model.Authority;

@Service
public interface IAuthorityService {
	Authority findById(Long id);

	Authority findByName(String name);
}
