package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.domain.CandidateProject;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateEducationService {


    private final CandidateService candidateService;

    @Transactional
    public void createEducationData(List<CandidateEducation> candidateEducationStagesFromRequest, Authentication authentication) {
        Candidate candidate = candidateService.findLoggedInCandidate();
        // Iteracja przez listę doświadczeń z requestu i dodanie ich do kandydata
        List<CandidateEducation> candidateEducationStages = candidate.getCandidateEducationStages();

        for (CandidateEducation educationStagesFromRequest : candidateEducationStagesFromRequest) {
            CandidateEducation candidateProject = buildCandidateEducation(educationStagesFromRequest);
            candidateEducationStages.add(candidateProject);
        }
        Candidate candidateWithEducation = candidate.withCandidateEducationStages(candidateEducationStages);

        candidateService.createCandidateExperience(candidateWithEducation);
    }

    private CandidateEducation buildCandidateEducation(CandidateEducation candidateEducationStagesFromRequest) {
        return CandidateEducation.builder()
                .candidateEducationId(candidateEducationStagesFromRequest.getCandidateEducationId())
                .institution(candidateEducationStagesFromRequest.getInstitution())
                .degree(candidateEducationStagesFromRequest.getDegree())
                .fieldOfStudy(candidateEducationStagesFromRequest.getFieldOfStudy())
                .fromDate(candidateEducationStagesFromRequest.getFromDate())
                .toDate(candidateEducationStagesFromRequest.getToDate())
                .build();
    }
}
