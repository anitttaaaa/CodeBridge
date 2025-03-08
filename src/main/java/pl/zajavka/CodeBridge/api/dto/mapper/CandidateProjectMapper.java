package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateProjectDTO;
import pl.zajavka.CodeBridge.domain.CandidateProject;

@Mapper(componentModel = "spring")
public interface CandidateProjectMapper {

    default CandidateProjectDTO mapToDto(CandidateProject candidateProject) {
        return new CandidateProjectDTO.Builder()
                .candidateProjectId(candidateProject.getCandidateProjectId())
                .projectTitle(candidateProject.getProjectTitle())
                .technologies(candidateProject.getTechnologies())
                .description(candidateProject.getDescription())
                .fromDate(candidateProject.getFromDate())
                .toDate(candidateProject.getToDate())
                .projectLink(candidateProject.getProjectLink())
                .candidateId(candidateProject.getCandidateId())
                .build();
    }

    default CandidateProject mapToDomain(CandidateProjectDTO candidateProjectDTO) {
        return new CandidateProject.Builder()
                .candidateProjectId(candidateProjectDTO.getCandidateProjectId())
                .projectTitle(candidateProjectDTO.getProjectTitle())
                .technologies(candidateProjectDTO.getTechnologies())
                .description(candidateProjectDTO.getDescription())
                .fromDate(candidateProjectDTO.getFromDate())
                .toDate(candidateProjectDTO.getToDate())
                .projectLink(candidateProjectDTO.getProjectLink())
                .candidateId(candidateProjectDTO.getCandidateId())
                .build();
    }
}
