package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateProjectDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateProjectMapper;
import pl.zajavka.CodeBridge.domain.CandidateProject;

@Component
public class CandidateProjectMapperImpl implements CandidateProjectMapper {

    @Override
    public CandidateProjectDTO mapToDto(CandidateProject candidateProject) {
        return new CandidateProjectDTO(
                candidateProject.getCandidateProjectId(),
                candidateProject.getProjectTitle(),
                candidateProject.getTechnologies(),
                candidateProject.getDescription(),
                candidateProject.getFromDate(),
                candidateProject.getToDate(),
                candidateProject.getProjectLink()
                );
    }

    @Override
    public CandidateProject mapToDomain(CandidateProjectDTO candidateProjectDTO) {
        return new CandidateProject.Builder()
                .candidateProjectId(candidateProjectDTO.getCandidateProjectId())
                .projectTitle(candidateProjectDTO.getProjectTitle())
                .technologies(candidateProjectDTO.getTechnologies())
                .description(candidateProjectDTO.getDescription())
                .fromDate(candidateProjectDTO.getFromDate())
                .toDate(candidateProjectDTO.getToDate())
                .projectLink(candidateProjectDTO.getProjectLink())
                .build();
    }
}
