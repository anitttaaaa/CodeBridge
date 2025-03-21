package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateEducationDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateEducationMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateEducationDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

import java.nio.file.AccessDeniedException;

@Service
public class CandidateEducationService {

    private final CandidateService candidateService;
    private final CandidateEducationDAO candidateEducationDAO;
    private final CandidateEducationMapper candidateEducationMapper;

@Autowired
    public CandidateEducationService(CandidateService candidateService,
                                     CandidateEducationDAO candidateEducationDAO,
                                     CandidateEducationMapper candidateEducationMapper) {
        this.candidateService = candidateService;
        this.candidateEducationDAO = candidateEducationDAO;
        this.candidateEducationMapper = candidateEducationMapper;
    }

    @Transactional
    public void createEducationData(CandidateEducationDTO candidateEducationFromRequest, Authentication authentication) {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();

        CandidateEducation candidateEducation = candidateEducationMapper.mapToDomain(candidateEducationFromRequest);

        candidateEducationDAO.createEducation(candidateEducation, candidateId);

    }

    public void updateCandidateEducation(CandidateEducationDTO candidateEducationDTO, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();

        CandidateEducation candidateEducation = candidateEducationMapper.mapToDomain(candidateEducationDTO);

        candidateEducationDAO.updateCandidateEducation(candidateEducation, candidateId);
    }

    public void deleteCandidateEducationById(Integer candidateEducationId) {

        candidateEducationDAO.deleteById(candidateEducationId);

    }
}
