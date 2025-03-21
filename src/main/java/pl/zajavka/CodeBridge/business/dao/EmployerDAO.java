package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.Optional;

public interface EmployerDAO {

    Optional<Employer> findByUserId(Integer userId);

    void createJobOffer(JobOffer employerAddJobOffer);

    Optional<Employer> findEmployerByEmail(String employerEmail);
}
