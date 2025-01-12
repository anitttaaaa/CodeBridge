package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.List;
import java.util.Optional;

public interface JobOfferDAO {


    List<JobOffer> findAllJobOffers();

    List<JobOffer> findAll();
    Optional<JobOffer> findById(Integer jobOfferId);

}
