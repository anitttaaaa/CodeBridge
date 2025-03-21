package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

public interface CandidateExperienceMapper {

    CandidateExperienceDTO mapToDto(CandidateExperience candidateExperience);

    CandidateExperience mapToDomain(CandidateExperienceDTO candidateExperienceDTO);
}
