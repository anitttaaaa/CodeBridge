package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.CandidateEducationDTO;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

public interface CandidateEducationMapper {

    CandidateEducationDTO mapToDto(CandidateEducation candidateEducation);

    CandidateEducation mapToDomain(CandidateEducationDTO candidateEducationDTO);
}
