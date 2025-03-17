package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;

public interface CandidateEducationEntityMapper {

    CandidateEducation mapFromEntity(CandidateEducationEntity candidateEducationEntity);

    CandidateEducationEntity mapToEntity(CandidateEducation candidateEducation, Integer candidateId);
}
