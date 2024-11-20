package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.Candidate;

import java.util.Optional;

public interface CandidateDAO {

    Optional<Candidate> findCandidateByEmail(String email);
    void updateCandidate(Candidate candidate);


    void createCandidateExperience(Candidate candidateWithExperience);
}
