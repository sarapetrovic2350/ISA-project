package ISA.BloodBank.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import ISA.BloodBank.model.DonorQuestionnaire;

@Service
public interface IDonorQuestionnaireService {

	DonorQuestionnaire saveQuestionnaire(DonorQuestionnaire questionnaire);
	List<DonorQuestionnaire> getAllQuestionnaires();
}
