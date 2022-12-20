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

	public DonorQuestionnaire getQuestionnareByUserId(Long Id) {
		List<DonorQuestionnaire> list = getAllQuestionnaires(); 
		DonorQuestionnaire retVal = new DonorQuestionnaire(); 
		for(int i=0; i< list.size(); i++) {
			if(list.get(i).getRegisteredUser().getUserId().equals(Id)) {
				retVal = list.get(i); 
			}
		}
		
		return retVal; 
	}
	
	public Boolean checkQuestionnare(Long Id) {
		
		DonorQuestionnaire questionnaire = getQuestionnareByUserId(Id); 
		
		if(questionnaire.getWeight() < 50) {
			return false; 
		}else if(questionnaire.getAge() < 18) {
			return false; 
		}else if(questionnaire.getGeneralGoodHealth().equals(false)) {
			return false; 
		}else if(questionnaire.getSymptomsOfIllness().equals(true)) {
			return false; 
		}else if(questionnaire.getNormalBloodPressure().equals(false)) {
			return false; 
		}else if(questionnaire.getSkinDisorders().equals(true)) {
			return false; 
		}else if(questionnaire.getUnderMedication().equals(true)) {
			return false; 
		}else if(questionnaire.getTattooOrPiercing().equals(true)){
			return false; 
		}else if(questionnaire.getRecentlyVisitedDentist().equals(true)){
			return false; 
		}else if(questionnaire.getRecentlyDonatedBlood().equals(true)){
			return false; 
		}else if(questionnaire.getHasPeriod().equals(true)){
			return false; 
		}
			
		return true; 
	}
}
