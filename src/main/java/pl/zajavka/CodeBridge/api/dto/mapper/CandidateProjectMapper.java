package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateProjectDTO;
import pl.zajavka.CodeBridge.domain.CandidateProject;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateProjectEntity;

@Mapper(componentModel = "spring")
public interface CandidateProjectMapper {

    default CandidateProjectDTO mapToDto(CandidateProject candidateProject) {
        return new CandidateProjectDTO(
                candidateProject.getCandidateProjectId(),
                candidateProject.getProjectTitle(),
                candidateProject.getTechnologies(),
                candidateProject.getDescription(),
                candidateProject.getFromDate(),
                candidateProject.getToDate(),
                candidateProject.getProjectLink(),
                candidateProject.getCandidateId()
        );
    }

    // Mapowanie obiektu CandidateProjectDTO na CandidateProject przy użyciu konstruktora
    default CandidateProject mapToDomain(CandidateProjectDTO candidateProjectDTO) {
        return new CandidateProject(
                candidateProjectDTO.getCandidateProjectId(),
                candidateProjectDTO.getProjectTitle(),
                candidateProjectDTO.getTechnologies(),
                candidateProjectDTO.getDescription(),
                candidateProjectDTO.getFromDate(),
                candidateProjectDTO.getToDate(),
                candidateProjectDTO.getProjectLink(),
                candidateProjectDTO.getCandidateId()
        );
    }
    default CandidateProject map(CandidateProjectEntity candidateProjectEntity) {
        return new CandidateProject(
                candidateProjectEntity.getCandidateProjectId(),
                candidateProjectEntity.getProjectTitle(),
                candidateProjectEntity.getTechnologies(),
                candidateProjectEntity.getDescription(),
                candidateProjectEntity.getFromDate(),
                candidateProjectEntity.getToDate(),
                candidateProjectEntity.getProjectLink(),
                candidateProjectEntity.getCandidateId()
        );
    }
}
