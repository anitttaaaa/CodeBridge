package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateProjectDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateProjectMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateProjectDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateProject;

@Service
public class CandidateProjectService {

    private final CandidateService candidateService;
    private final CandidateProjectDAO candidateProjectDAO;
    private final CandidateProjectMapper candidateProjectMapper;

    @Autowired
    public CandidateProjectService(CandidateService candidateService,
                                   CandidateProjectDAO candidateProjectDAO,
                                   CandidateProjectMapper candidateProjectMapper) {
        this.candidateService = candidateService;
        this.candidateProjectDAO = candidateProjectDAO;
        this.candidateProjectMapper = candidateProjectMapper;
    }

    @Transactional
    public void createProjectData(CandidateProjectDTO candidateProjectFromRequest, Authentication authentication) {

        String candidateEmail = authentication.getName();
        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();

        CandidateProject candidateProject = candidateProjectMapper.mapToDomain(candidateProjectFromRequest);

        if (candidateProject == null) {
            throw new NullPointerException("Mapping CandidateProjectDTO to CandidateProject failed");
        }

        candidateProjectDAO.createProject(candidateProject, candidateId);
    }


    public void updateCandidateProject(CandidateProjectDTO candidateProjectDTO, Authentication authentication) {
        if (candidateProjectDTO == null) {
            throw new NullPointerException("CandidateProjectDTO cannot be null");
        }
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();

        CandidateProject candidateProject = candidateProjectMapper.mapToDomain(candidateProjectDTO);

        if (candidateProject == null) {
            throw new NullPointerException("Mapping to CandidateProject failed");
        }

        candidateProjectDAO.updateCandidateProject(candidateProject, candidateId);
    }


    public void deleteCandidateProjectById(Integer candidateProjectId) {
        candidateProjectDAO.deleteById(candidateProjectId);
    }
}
