package ISA.BloodBank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.DonorQuestionnaireDTO;
import ISA.BloodBank.iservice.IDonorQuestionnaireService;
import ISA.BloodBank.model.DonorQuestionnaire;
import ISA.BloodBank.model.RegisteredUser;
import ISA.BloodBank.repository.IDonorQuestionnaireRepository;
import ISA.BloodBank.repository.IUserRepository;

@Service
public class DonorQuestionnaireService implements IDonorQuestionnaireService{

	private IDonorQuestionnaireRepository donorQuestionnaireRepository;
	private IUserRepository userRepository;

	public DonorQuestionnaireService(IDonorQuestionnaireRepository donorQuestionnaireRepository,
			IUserRepository userRepository) {
		super();
		this.donorQuestionnaireRepository = donorQuestionnaireRepository;
		this.userRepository = userRepository;
	}

	@Override
	public DonorQuestionnaire saveQuestionnaire(DonorQuestionnaireDTO questionnaireDTO) {
		DonorQuestionnaire questionnaire = new DonorQuestionnaire();
		RegisteredUser u = (RegisteredUser) userRepository.findByEmail(questionnaireDTO.getUserEmail());
		questionnaire.setRegisteredUser(u);
		questionnaire.setWeight(questionnaireDTO.getWeight());
		questionnaire.setAge(questionnaireDTO.getAge());
		questionnaire.setGeneralGoodHealth(questionnaireDTO.getGeneralGoodHealth());
		questionnaire.setSymptomsOfIllness(questionnaireDTO.getSymptomsOfIllness());
		questionnaire.setUnderMedication(questionnaireDTO.getUnderMedication());
		questionnaire.setNormalBloodPressure(questionnaireDTO.getNormalBloodPressure());
		questionnaire.setSkinDisorders(questionnaireDTO.getSkinDisorders());
		questionnaire.setTattooOrPiercing(questionnaireDTO.getTattooOrPiercing());
		questionnaire.setRecentlyVisitedDentist(questionnaireDTO.getRecentlyVisitedDentist());
		questionnaire.setRecentlyDonatedBlood(questionnaireDTO.getRecentlyDonatedBlood());
		questionnaire.setHasPeriod(questionnaireDTO.getHasPeriod());
		return donorQuestionnaireRepository.save(questionnaire);
	}

	@Override
	public List<DonorQuestionnaire> getAllQuestionnaires() {
		return donorQuestionnaireRepository.findAll();
	}

}
