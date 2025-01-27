package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

@Mapper(componentModel = "spring")
public interface CandidateExperienceMapper {

    CandidateExperienceDTO mapToDto(CandidateExperience candidateExperience);
    CandidateExperience mapToDomain(CandidateExperienceDTO candidateExperienceDTO);
}
