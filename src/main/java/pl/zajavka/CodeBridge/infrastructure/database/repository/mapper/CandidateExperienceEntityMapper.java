package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;

public interface CandidateExperienceEntityMapper {

    CandidateExperience mapFromEntity(CandidateExperienceEntity candidateExperienceEntity);

    CandidateExperienceEntity mapToEntity(CandidateExperience candidateExperience);
}
