package ISA.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.DonorQuestionnaire;

@Repository
public interface IDonorQuestionnaireRepository extends JpaRepository<DonorQuestionnaire, Long> {

}
