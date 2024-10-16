package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;

import java.util.Optional;

public interface CandidateDAO {

    Optional<Candidate> findCandidateByEmail(String email);
    void updateCandidatePhoto(Candidate candidate);
    void saveCandidate(Candidate candidate);

}
