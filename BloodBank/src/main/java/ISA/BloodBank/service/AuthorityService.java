package ISA.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.BloodBank.iservice.IAuthorityService;
import ISA.BloodBank.model.Authority;
import ISA.BloodBank.repository.IAuthorityRepository;

@Service
public class AuthorityService implements IAuthorityService {

	private IAuthorityRepository authorityRepository;
	
	@Autowired
	public AuthorityService(IAuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Authority findById(Long authorityId) {
		Authority authority = authorityRepository.getOne(authorityId);
		return authority;
	}

	@Override
	public Authority findByName(String name) {
		Authority authority = authorityRepository.findByName(name);
		return authority;
	}
}
