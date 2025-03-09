package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateEducationDTO;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

public interface CandidateEducationMapper {

    // Mapowanie z domeny do DTO
    CandidateEducationDTO mapToDto(CandidateEducation candidateEducation);

    // Mapowanie z DTO do domeny
    CandidateEducation mapToDomain(CandidateEducationDTO candidateEducationDTO);
}
