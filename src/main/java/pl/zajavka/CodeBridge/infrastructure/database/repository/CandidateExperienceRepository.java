package pl.zajavka.CodeBridge.infrastructure.database.repository;

import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateExperienceDAO;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateExperienceJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateExperienceEntityMapper;

@Repository
public class CandidateExperienceRepository implements CandidateExperienceDAO {

    private final CandidateExperienceEntityMapper candidateExperienceEntityMapper;
    private final CandidateExperienceJpaRepository candidateExperienceJpaRepository;

    public CandidateExperienceRepository(CandidateExperienceEntityMapper candidateExperienceEntityMapper,
                                         CandidateExperienceJpaRepository candidateExperienceJpaRepository) {
        this.candidateExperienceEntityMapper = candidateExperienceEntityMapper;
        this.candidateExperienceJpaRepository = candidateExperienceJpaRepository;
    }

    @Override
    public CandidateExperience createExperience(CandidateExperience candidateExperience, Integer candidateId) {

        CandidateExperienceEntity candidateExperienceToSave = candidateExperienceEntityMapper.mapToEntity(candidateExperience, candidateId);
        CandidateExperienceEntity candidateExperienceSaved = candidateExperienceJpaRepository.saveAndFlush(candidateExperienceToSave);

        return candidateExperienceEntityMapper.mapFromEntity(candidateExperienceSaved);

    }

    @Override
    public void updateCandidateExperience(CandidateExperience candidateExperienceToUpdate, Integer candidateId) {
        CandidateExperienceEntity experienceToSave = candidateExperienceEntityMapper.mapToEntity(candidateExperienceToUpdate, candidateId);
        candidateExperienceJpaRepository.saveAndFlush(experienceToSave);
    }

    @Override
    public void deleteById(Integer candidateExperienceId) {
        candidateExperienceJpaRepository.deleteById(candidateExperienceId);
    }


}

