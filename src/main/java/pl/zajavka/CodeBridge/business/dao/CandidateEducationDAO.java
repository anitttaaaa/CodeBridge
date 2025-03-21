package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateEducation;

public interface CandidateEducationDAO {

    CandidateEducation createEducation(CandidateEducation candidateEducation, Integer candidateId);

    void updateCandidateEducation(CandidateEducation candidateEducation, Integer candidateId);

    void deleteById(Integer candidateEducationId);
}
