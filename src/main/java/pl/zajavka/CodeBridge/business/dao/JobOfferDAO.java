package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.List;

public interface JobOfferDAO {


    List<JobOffer> findAllJobOffers();

}
