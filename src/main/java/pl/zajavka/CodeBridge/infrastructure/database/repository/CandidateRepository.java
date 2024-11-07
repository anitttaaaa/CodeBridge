package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateExperienceJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateEntityMapper;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateExperienceEntityMapper;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CandidateRepository implements CandidateDAO {

    private final CandidateJpaRepository candidateJpaRepository;
    private final CandidateExperienceJpaRepository candidateExperienceJpaRepository;

    private final CandidateEntityMapper candidateEntityMapper;
    private final CandidateExperienceEntityMapper candidateExperienceEntityMapper;



    @Override
    public Optional<Candidate> findCandidateByEmail(String email) {
        return candidateJpaRepository.findByEmail(email)
                .map(candidateEntityMapper::mapFromEntity);
    }


    public void saveCandidate(Candidate candidate) {

        CandidateEntity candidateToSave = candidateEntityMapper.mapToEntity(candidate);
        CandidateEntity candidateSaved = candidateJpaRepository.saveAndFlush(candidateToSave);


    }

    public void updateCandidate(Candidate candidate) {
        CandidateEntity candidateEntity = candidateEntityMapper.mapToEntity(candidate);
        System.out.println("correctly done");
        candidateJpaRepository.saveAndFlush(candidateEntity);
    }

    @Override
    public void createCandidateExperience(Candidate candidateToDatabase) {

        CandidateEntity candidateToSave = candidateEntityMapper.mapToEntity(candidateToDatabase);
        CandidateEntity candidateSaved = candidateJpaRepository.saveAndFlush(candidateToSave);

        candidateToDatabase.getCandidateExperiences().stream()
                .filter(experience -> Objects.isNull(experience.getCandidateExperienceId()))
                .map(candidateExperienceEntityMapper::mapToEntity)
                .forEach(candidateExperienceEntity -> {
                    (candidateExperienceEntity).setCandidate(candidateSaved);
                    candidateExperienceJpaRepository.saveAndFlush(candidateExperienceEntity);
                });
    }
}














