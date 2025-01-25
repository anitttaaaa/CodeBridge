package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;

import java.util.List;
import java.util.Optional;

public interface CandidateDAO {

    Optional<Candidate> findCandidateByEmail(String email);
    void updateCandidate(Candidate candidate);

    List<Candidate> findAll();

    List<Candidate> findAllCandidates();

   Optional<Candidate> findById(Integer candidateId);
}
