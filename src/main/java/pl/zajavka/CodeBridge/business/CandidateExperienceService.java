package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateExperienceDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class CandidateExperienceService {


    private final CandidateService candidateService;
    private final CandidateExperienceDAO candidateExperienceDAO;



    @Transactional
    public void createExperienceData(CandidateExperience candidateExperienceFromRequest) {

        Candidate candidate = candidateService.findLoggedInCandidate();


        CandidateExperience candidateExperience = buildCandidateExperience(candidateExperienceFromRequest, candidate);
        candidateExperienceDAO.createExperience(candidateExperience);
    }


    private CandidateExperience buildCandidateExperience(CandidateExperience candidateExperienceFromRequest, Candidate candidate) {
        return CandidateExperience.builder()
                .companyName(candidateExperienceFromRequest.getCompanyName())
                .candidatePosition(candidateExperienceFromRequest.getCandidatePosition())
                .description(candidateExperienceFromRequest.getDescription())
                .fromDate(candidateExperienceFromRequest.getFromDate())
                .toDate(candidateExperienceFromRequest.getToDate())
                .candidateId(candidate.getCandidateId())
                .build();
    }

    @Transactional
    public void updateCandidateExperience(CandidateExperience candidateExperience, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer loggedInCandidateId = candidate.getCandidateId();

        if (!candidateExperience.getCandidateId().equals(loggedInCandidateId)){
            throw new AccessDeniedException("Unauthorized access.");
        }

        candidateExperienceDAO.updateCandidateExperience(candidateExperience);
    }

    public void deleteCandidateExperienceById(Integer candidateExperienceId) {

        candidateExperienceDAO.deleteById(candidateExperienceId);
    }
}
