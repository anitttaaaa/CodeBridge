package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateExperience;

public interface CandidateExperienceDAO {


    CandidateExperience createExperience(CandidateExperience candidateExperience);

    void updateCandidateExperience(CandidateExperience candidateExperience);

    void deleteById(Integer candidateExperienceId);
}
