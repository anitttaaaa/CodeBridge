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

import java.nio.file.AccessDeniedException;

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

        candidateProjectDAO.createProject(candidateProject, candidateId);
    }


    public void updateCandidateProject(CandidateProjectDTO candidateProjectDTO, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();

        CandidateProject candidateProject = candidateProjectMapper.mapToDomain(candidateProjectDTO);

        candidateProjectDAO.updateCandidateProject(candidateProject, candidateId);
    }


    public void deleteCandidateProjectById(Integer candidateProjectId) {
        candidateProjectDAO.deleteById(candidateProjectId);
    }
}
