package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.util.List;

public interface CandidateExperienceDAO {


    CandidateExperience createExperience(CandidateExperience candidateExperience);

    List<CandidateExperience> findExperienceByCandidateId(Integer candidateId);
}
