package ISA.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ISA.BloodBank.model.Report; 

@Repository
public interface IReportRepository extends JpaRepository<Report, Long> {

}
