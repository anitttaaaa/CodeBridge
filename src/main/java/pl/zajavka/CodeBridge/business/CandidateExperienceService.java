package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidateExperienceService {

    private final CodeBridgeUserDetailsService codeBridgeUserDetailsService;
    private final CandidateService candidateService;

    @Transactional
    public void createExperienceData(CandidateExperience candidateExperienceFromRequest, Authentication authentication) {

        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateExperience candidateExperience = buildCandidateExperience(candidateExperienceFromRequest);

        List<CandidateExperience> candidateExperiences = candidate.getCandidateExperiences();
        candidateExperiences.add(candidateExperience);

        Candidate candidateWithExperience = candidate.withCandidateExperiences(candidateExperiences);

    candidateService.createCandidateExperience(candidateWithExperience);

    }
    // New method to get candidate experiences
    private CandidateExperience buildCandidateExperience(CandidateExperience candidateExperienceFromRequest) {
        return CandidateExperience.builder()
                .companyName(candidateExperienceFromRequest.getCompanyName())
                .candidatePosition(candidateExperienceFromRequest.getCandidatePosition())
                .description(candidateExperienceFromRequest.getDescription())
                .fromDate(candidateExperienceFromRequest.getFromDate())
                .toDate(candidateExperienceFromRequest.getToDate())
                .build();
    }

    public List<CandidateExperienceDTO> getExperienceData(Authentication authentication) {
        Candidate candidate = candidateService.findLoggedInCandidate();
        return candidate.getCandidateExperiences().stream()
                .map(this::toCandidateExperienceDTO)
                .collect(Collectors.toList());
    }

    private CandidateExperienceDTO toCandidateExperienceDTO(CandidateExperience experience) {
        return CandidateExperienceDTO.builder()
                .candidateExperienceId(experience.getCandidateExperienceId())
                .companyName(experience.getCompanyName())
                .candidatePosition(experience.getCandidatePosition())
                .description(experience.getDescription())
                .fromDate(experience.getFromDate())
                .toDate(experience.getToDate())
                .build();
    }

}
