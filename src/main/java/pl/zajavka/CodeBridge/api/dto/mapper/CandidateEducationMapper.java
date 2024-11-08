package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateEducationDTO;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

@Mapper(componentModel = "spring")
public interface CandidateEducationMapper {

    CandidateEducationDTO mapToDto(CandidateEducation candidateEducation);

    CandidateEducation mapToDomain(CandidateEducationDTO candidateEducationDTO);
}
