package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateProject;

import java.util.List;
@Service
@AllArgsConstructor
public class CandidateProjectService {


    private final CandidateService candidateService;

    @Transactional
    public void createProjectData(List<CandidateProject> candidateProjectsFromRequest, Authentication authentication) {
        Candidate candidate = candidateService.findLoggedInCandidate();
        // Iteracja przez listę doświadczeń z requestu i dodanie ich do kandydata
        List<CandidateProject> candidateProjects = candidate.getCandidateProjects();

        for (CandidateProject projectFromRequest : candidateProjectsFromRequest) {
            CandidateProject candidateProject = buildCandidateProject(projectFromRequest);
            candidateProjects.add(candidateProject);
        }
        Candidate candidateWithProject = candidate.withCandidateProjects(candidateProjects);

        candidateService.createCandidateExperience(candidateWithProject);
    }

    private CandidateProject buildCandidateProject(CandidateProject candidateProjectFromRequest) {
        return CandidateProject.builder()
                .candidateProjectId(candidateProjectFromRequest.getCandidateProjectId())
                .projectTitle(candidateProjectFromRequest.getProjectTitle())
                .description(candidateProjectFromRequest.getDescription())
                .technologies(candidateProjectFromRequest.getTechnologies())
                .fromDate(candidateProjectFromRequest.getFromDate())
                .projectLink(candidateProjectFromRequest.getProjectLink())
                .build();
    }
}
