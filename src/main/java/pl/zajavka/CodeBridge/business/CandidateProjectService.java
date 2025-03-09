package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateProjectDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateProject;

import java.nio.file.AccessDeniedException;

@Service
public class CandidateProjectService {

    private final CandidateService candidateService;
    private final CandidateProjectDAO candidateProjectDAO;
@Autowired
    public CandidateProjectService(CandidateService candidateService,
                                   CandidateProjectDAO candidateProjectDAO) {
        this.candidateService = candidateService;
        this.candidateProjectDAO = candidateProjectDAO;
    }

    @Transactional
    public void createProjectData(CandidateProject candidateProjectFromRequest) {

        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateProject candidateProject = buildCandidateProject(candidateProjectFromRequest, candidate);
        candidateProjectDAO.createProject(candidateProject);
    }

    private CandidateProject buildCandidateProject(CandidateProject candidateProjectFromRequest, Candidate candidate) {
        return new CandidateProject.Builder()
                .projectTitle(candidateProjectFromRequest.getProjectTitle())
                .technologies(candidateProjectFromRequest.getTechnologies())
                .description(candidateProjectFromRequest.getDescription())
                .fromDate(candidateProjectFromRequest.getFromDate())
                .toDate(candidateProjectFromRequest.getToDate())
                .projectLink(candidateProjectFromRequest.getProjectLink())
                .candidateId(candidate.getCandidateId())
                .build();  // Budowanie obiektu CandidateProject przy użyciu Buildera
    }

    public void updateCandidateProject(CandidateProject candidateProject, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findLoggedInCandidate();
        Integer loggedInCandidateId = candidate.getCandidateId();

        if(!candidateProject.getCandidateId().equals(loggedInCandidateId)){
            throw new AccessDeniedException("Unauthorized access.");
        }
        candidateProjectDAO.updateCandidateProject(candidateProject);
    }


    public void deleteCandidateProjectById(Integer candidateProjectId) {
        candidateProjectDAO.deleteById(candidateProjectId);
    }
}
