package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateEducation;

public interface CandidateEducationDAO {

    CandidateEducation createEducation(CandidateEducation candidateEducation);

    void updateCandidateEducation(CandidateEducation candidateEducation);

    void deleteById(Integer candidateEducationId);
}
