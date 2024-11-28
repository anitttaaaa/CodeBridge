package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.util.Optional;

public interface CandidateExperienceDAO {


    CandidateExperience createExperience(CandidateExperience candidateExperience);


    void updateCandidateExperience(CandidateExperience candidateExperience);


    void deleteById(Integer candidateExperienceId);
}
