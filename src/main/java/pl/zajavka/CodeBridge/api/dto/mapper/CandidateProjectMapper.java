package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.CandidateProjectDTO;
import pl.zajavka.CodeBridge.domain.CandidateProject;

public interface CandidateProjectMapper {

    CandidateProjectDTO mapToDto(CandidateProject candidateProject);

    CandidateProject mapToDomain(CandidateProjectDTO candidateProjectDTO);
}
