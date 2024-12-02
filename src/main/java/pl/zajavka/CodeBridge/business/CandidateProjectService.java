package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateProjectDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateProject;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class CandidateProjectService {

    private final CandidateService candidateService;
    private final CandidateProjectDAO candidateProjectDAO;

    @Transactional
    public void createProjectData(CandidateProject candidateProjectFromRequest) {

        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateProject candidateProject = buildCandidateProject(candidateProjectFromRequest, candidate);
        candidateProjectDAO.createProject(candidateProject);


    }

    private CandidateProject buildCandidateProject(CandidateProject candidateProjectFromRequest, Candidate candidate) {
        return CandidateProject.builder()
                .projectTitle(candidateProjectFromRequest.getProjectTitle())
                .technologies(candidateProjectFromRequest.getTechnologies())
                .description(candidateProjectFromRequest.getDescription())
                .fromDate(candidateProjectFromRequest.getFromDate())
                .toDate(candidateProjectFromRequest.getToDate())
                .projectLink(candidateProjectFromRequest.getProjectLink())
                .candidateId(candidate.getCandidateId())
                .build();
    }

    public void updateCandidateProject(CandidateProject candidateProject, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findLoggedInCandidate();
        Integer loggedInCandidateId = candidate.getCandidateId();

        if(!candidateProject.getCandidateId().equals(loggedInCandidateId)){
            throw new AccessDeniedException("Unauthorized access.");
        }
        candidateProjectDAO.updateCandidateProject(candidateProject);

    }

    public void deleteCandidateProjectById(Integer candidateProjectId, Authentication authentication) {
        candidateProjectDAO.deleteById(candidateProjectId);
    }
}
