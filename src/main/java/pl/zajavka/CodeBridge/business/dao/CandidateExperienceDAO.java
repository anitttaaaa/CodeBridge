package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateExperience;

public interface CandidateExperienceDAO {


    CandidateExperience createExperience(CandidateExperience candidateExperience, Integer candidateId);

    void updateCandidateExperience(CandidateExperience candidateExperience, Integer candidateId);

    void deleteById(Integer candidateExperienceId);
}
