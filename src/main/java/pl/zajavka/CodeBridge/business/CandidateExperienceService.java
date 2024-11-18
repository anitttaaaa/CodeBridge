package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateExperienceDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.repository.CandidateExperienceRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateExperienceEntityMapper;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateExperienceService {

    private final CodeBridgeUserDetailsService codeBridgeUserDetailsService;
    private final CandidateService candidateService;
    private final CandidateExperienceDAO candidateExperienceDAO;
    private final CandidateExperienceRepository candidateExperienceRepository;
    private final CandidateExperienceEntityMapper candidateExperienceEntityMapper;

    @Transactional
    public void createExperienceData(CandidateExperience candidateExperienceFromRequest, Authentication authentication) {

        Candidate candidate = candidateService.findLoggedInCandidate();

        CandidateExperience candidateExperience = buildCandidateExperience(candidateExperienceFromRequest, candidate);

        CandidateExperience candidateExperienceSaved = candidateExperienceDAO.createExperience(candidateExperience);

        candidate.getCandidateExperiences().add(candidateExperienceSaved);

//        candidate.getCandidateExperiences().add(candidateExperience);

        candidateService.updateCandidate(candidate, authentication);
    }


    private CandidateExperience buildCandidateExperience(CandidateExperience candidateExperienceFromRequest, Candidate candidate) {
        return CandidateExperience.builder()
                .companyName(candidateExperienceFromRequest.getCompanyName())
                .candidatePosition(candidateExperienceFromRequest.getCandidatePosition())
                .description(candidateExperienceFromRequest.getDescription())
                .fromDate(candidateExperienceFromRequest.getFromDate())
                .toDate(candidateExperienceFromRequest.getToDate())
                .candidate(candidate)
                .build();
    }


    public List<CandidateExperience> findExperienceByCandidateId(Integer candidateId) {

        return candidateExperienceDAO.findExperienceByCandidateId(candidateId);

    }
}
