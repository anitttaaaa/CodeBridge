package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateProjectDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateProjectMapper;
import pl.zajavka.CodeBridge.domain.CandidateProject;

@Component
public class CandidateProjectMapperImpl implements CandidateProjectMapper {

    @Override
    public CandidateProjectDTO mapToDto(CandidateProject candidateProject) {
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
                .candidateId(candidateProjectDTO.getCandidateId())
                .build();
    }
}
