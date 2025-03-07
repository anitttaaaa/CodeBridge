package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateExperienceJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateEntityMapper;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateExperienceEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private void saveCandidateEntity(Candidate candidate) {
        CandidateEntity candidateEntity = candidateEntityMapper.mapToEntity(candidate);
        candidateJpaRepository.saveAndFlush(candidateEntity);
    }

    public void updateCandidate(Candidate candidate) {
        saveCandidateEntity(candidate);
    }

    @Override
    public List<Candidate> findAll() {
        List<CandidateEntity> candidateEntities = candidateJpaRepository.findAll();

        return candidateEntities.stream().
                map(candidateEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Candidate> findAllCandidates() {

        List<Candidate> candidates = candidateJpaRepository.findAll().stream()
                .map(candidateEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
        return candidates;
    }

    @Override
    public Optional<Candidate> findById(Integer candidateId) {
        return candidateJpaRepository.findById(candidateId)
                .map(candidateEntityMapper::mapFromEntity);
    }


}









