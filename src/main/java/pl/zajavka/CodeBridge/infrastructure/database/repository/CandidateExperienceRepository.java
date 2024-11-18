package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateExperienceDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateExperienceJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateExperienceEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CandidateExperienceRepository implements CandidateExperienceDAO {

    private final CandidateExperienceEntityMapper candidateExperienceEntityMapper;
    private final CandidateExperienceJpaRepository candidateExperienceJpaRepository;


    @Override
    public CandidateExperience createExperience(CandidateExperience candidateExperience) {

        CandidateExperienceEntity candidateExperienceToSave = candidateExperienceEntityMapper.mapToEntity(candidateExperience);
        CandidateExperienceEntity candidateExperienceSaved = candidateExperienceJpaRepository.saveAndFlush(candidateExperienceToSave);

        return candidateExperienceEntityMapper.mapFromEntity(candidateExperienceSaved);

    }

    @Override
    public List<CandidateExperience> findExperienceByCandidateId(Integer candidateId) {
        List<CandidateExperienceEntity> candidateExperienceEntities = candidateExperienceJpaRepository.findExperienceByCandidateId(candidateId);

        return candidateExperienceEntities.stream()
                .map(candidateExperienceEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }


}

