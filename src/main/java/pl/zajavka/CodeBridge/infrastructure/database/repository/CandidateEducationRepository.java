package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateEducationDAO;
import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateEducationJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateEducationEntityMapper;

@Repository
@AllArgsConstructor
public class CandidateEducationRepository implements CandidateEducationDAO {

    CandidateEducationEntityMapper candidateEducationEntityMapper;
    CandidateEducationJpaRepository candidateEducationJpaRepository;

    @Override
    public CandidateEducation createEducation(CandidateEducation candidateEducation) {

        CandidateEducationEntity candidateEducationToSave = candidateEducationEntityMapper.mapToEntity(candidateEducation);
        CandidateEducationEntity candidateEducationSaved = candidateEducationJpaRepository.saveAndFlush(candidateEducationToSave);

        return candidateEducationEntityMapper.mapFromEntity(candidateEducationSaved);
    }

    @Override
    public void updateCandidateEducation(CandidateEducation candidateEducationToUpdate) {
        CandidateEducationEntity educationToSave = candidateEducationEntityMapper.mapToEntity(candidateEducationToUpdate);
        candidateEducationJpaRepository.saveAndFlush(educationToSave);
    }

    @Override
    public void deleteById(Integer candidateEducationId) {
        candidateEducationJpaRepository.deleteById(candidateEducationId);
    }
}
