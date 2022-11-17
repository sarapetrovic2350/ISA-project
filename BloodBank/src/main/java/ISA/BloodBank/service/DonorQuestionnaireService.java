package ISA.BloodBank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.iservice.IDonorQuestionnaireService;
import ISA.BloodBank.model.DonorQuestionnaire;
import ISA.BloodBank.repository.IDonorQuestionnaireRepository;

@Service
public class DonorQuestionnaireService implements IDonorQuestionnaireService{

	private IDonorQuestionnaireRepository donorQuestionnaireRepository;
	
	public DonorQuestionnaireService(IDonorQuestionnaireRepository donorQuestionnaireRepository) {
		super();
		this.donorQuestionnaireRepository = donorQuestionnaireRepository;
	}

	@Override
	public DonorQuestionnaire saveQuestionnaire(DonorQuestionnaire questionnaire) {
		return donorQuestionnaireRepository.save(questionnaire);
	}

	@Override
	public List<DonorQuestionnaire> getAllQuestionnaires() {
		return donorQuestionnaireRepository.findAll();
	}

}
