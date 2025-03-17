package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateExperienceMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateExperienceDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.nio.file.AccessDeniedException;

@Service
public class CandidateExperienceService {
@Autowired
    public CandidateExperienceService(CandidateService candidateService,
                                      CandidateExperienceDAO candidateExperienceDAO,
                                      CandidateExperienceMapper candidateExperienceMapper) {
        this.candidateService = candidateService;
        this.candidateExperienceDAO = candidateExperienceDAO;
        this.candidateExperienceMapper = candidateExperienceMapper;
    }

    private final CandidateService candidateService;
    private final CandidateExperienceDAO candidateExperienceDAO;
    private final CandidateExperienceMapper candidateExperienceMapper;




    @Transactional
    public void createExperienceData(CandidateExperienceDTO candidateExperienceFromRequest, Authentication authentication) throws AccessDeniedException {

        String candidateEmail = authentication.getName();
        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();

        CandidateExperience candidateExperience = candidateExperienceMapper.mapToDomain(candidateExperienceFromRequest);


        candidateExperienceDAO.createExperience(candidateExperience, candidateId);

    }


    @Transactional
    public void updateCandidateExperience(CandidateExperienceDTO candidateExperienceDTO, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();


        CandidateExperience candidateExperience = candidateExperienceMapper.mapToDomain(candidateExperienceDTO);
        candidateExperienceDAO.updateCandidateExperience(candidateExperience,candidateId);
    }


    public void deleteCandidateExperienceById(Integer candidateExperienceId) {

        candidateExperienceDAO.deleteById(candidateExperienceId);
    }
}
