package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateExperienceDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

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


}