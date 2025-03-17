package pl.zajavka.CodeBridge.infrastructure.database.repository;

import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateEducationDAO;
import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateEducationJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateEducationEntityMapper;

@Repository
public class CandidateEducationRepository implements CandidateEducationDAO {

    CandidateEducationEntityMapper candidateEducationEntityMapper;
    CandidateEducationJpaRepository candidateEducationJpaRepository;

    public CandidateEducationRepository(CandidateEducationEntityMapper candidateEducationEntityMapper,
                                        CandidateEducationJpaRepository candidateEducationJpaRepository) {
        this.candidateEducationEntityMapper = candidateEducationEntityMapper;
        this.candidateEducationJpaRepository = candidateEducationJpaRepository;
    }

    @Override
    public CandidateEducation createEducation(CandidateEducation candidateEducation, Integer candidateId) {

        CandidateEducationEntity candidateEducationToSave = candidateEducationEntityMapper.mapToEntity(candidateEducation, candidateId);
        CandidateEducationEntity candidateEducationSaved = candidateEducationJpaRepository.saveAndFlush(candidateEducationToSave);

        return candidateEducationEntityMapper.mapFromEntity(candidateEducationSaved);
    }

    @Override
    public void updateCandidateEducation(CandidateEducation candidateEducationToUpdate, Integer candidateId) {

        CandidateEducationEntity educationToSave = candidateEducationEntityMapper.mapToEntity(candidateEducationToUpdate, candidateId);
        candidateEducationJpaRepository.saveAndFlush(educationToSave);
    }

    @Override
    public void deleteById(Integer candidateEducationId) {
        candidateEducationJpaRepository.deleteById(candidateEducationId);
    }
}
