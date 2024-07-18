package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.Employer;

import java.util.Optional;

public interface EmployerDAO {

    Optional<Employer> findByUserId(Integer userId);

    void createJobOffer(Employer employerAddJobOffer);

}
