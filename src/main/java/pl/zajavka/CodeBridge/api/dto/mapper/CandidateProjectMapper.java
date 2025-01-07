package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateProjectDTO;
import pl.zajavka.CodeBridge.domain.CandidateProject;

@Mapper(componentModel = "spring")
public interface CandidateProjectMapper {

    CandidateProjectDTO mapToDto(CandidateProject candidateProject);
    CandidateProject mapToDomain(CandidateProjectDTO candidateProjectDTO);
}
