package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateExperienceDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.nio.file.AccessDeniedException;

@Service
public class CandidateExperienceService {
@Autowired
    public CandidateExperienceService(CandidateService candidateService,
                                      CandidateExperienceDAO candidateExperienceDAO) {
        this.candidateService = candidateService;
        this.candidateExperienceDAO = candidateExperienceDAO;
    }

    private final CandidateService candidateService;
    private final CandidateExperienceDAO candidateExperienceDAO;


    @Transactional
    public void createExperienceData(CandidateExperience candidateExperienceFromRequest, Authentication authentication) {

        String candidateEmail = authentication.getName();
        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();

        CandidateExperience candidateExperience = buildCandidateExperience(candidateExperienceFromRequest, candidateId);
        candidateExperienceDAO.createExperience(candidateExperience);
    }


    private CandidateExperience buildCandidateExperience(CandidateExperience candidateExperienceFromRequest, Integer candidateId) {
        return new CandidateExperience.Builder()
                .candidateExperienceId(candidateExperienceFromRequest.getCandidateExperienceId())  // Jeśli chcesz zachować istniejący ID
                .companyName(candidateExperienceFromRequest.getCompanyName())
                .candidatePosition(candidateExperienceFromRequest.getCandidatePosition())
                .description(candidateExperienceFromRequest.getDescription())
                .fromDate(candidateExperienceFromRequest.getFromDate())
                .toDate(candidateExperienceFromRequest.getToDate())
                .candidateId(candidateId)
                .build();
    }



    @Transactional
    public void updateCandidateExperience(CandidateExperience candidateExperience, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer loggedInCandidateId = candidate.getCandidateId();

        if (!candidateExperience.getCandidateId().equals(loggedInCandidateId)) {
            throw new AccessDeniedException("Unauthorized access.");
        }

        candidateExperienceDAO.updateCandidateExperience(candidateExperience);
    }


    public void deleteCandidateExperienceById(Integer candidateExperienceId) {

        candidateExperienceDAO.deleteById(candidateExperienceId);
    }
}
