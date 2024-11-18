package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

@Mapper(componentModel = "spring")
public interface CandidateExperienceMapper {

    CandidateExperienceDTO mapToDto(CandidateExperience candidateExperience);

    @Mapping(target = "candidate", source = "candidate", ignore = true)
    CandidateExperience mapFromDTO(CandidateExperienceDTO candidateExperienceDTO);
}
