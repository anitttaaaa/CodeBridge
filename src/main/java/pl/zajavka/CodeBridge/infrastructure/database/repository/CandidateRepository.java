package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CandidateRepository implements CandidateDAO {

    private final CandidateJpaRepository candidateJpaRepository;

    private final CandidateEntityMapper candidateEntityMapper;

    @Override
    public Optional<Candidate> findCandidateByEmail(String email) {
        return candidateJpaRepository.findByEmail(email)
                .map(candidateEntityMapper::mapFromEntity);
    }


    public void saveCandidate(Candidate candidate) {

        CandidateEntity candidateToSave = candidateEntityMapper.mapToEntity(candidate);
        CandidateEntity candidateSaved = candidateJpaRepository.saveAndFlush(candidateToSave);


    }

    public void updateCandidatePhoto(Candidate candidate) {
        CandidateEntity candidateEntity = candidateEntityMapper.mapToEntity(candidate);

        System.out.println("Preparing to save profilePhoto for candidate with email: " + candidateEntity.getEmail());

        candidateJpaRepository.saveAndFlush(candidateEntity);
    }
}
