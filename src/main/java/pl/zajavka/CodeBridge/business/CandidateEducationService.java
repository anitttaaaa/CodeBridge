package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateEducationDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class CandidateEducationService {

    private final CandidateService candidateService;
    private final CandidateEducationDAO candidateEducationDAO;

    @Transactional
    public void createEducationData(CandidateEducation candidateEducationFromRequest) {

        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateEducation candidateEducation = buildCandidateEducation(candidateEducationFromRequest, candidate);
        candidateEducationDAO.createEducation(candidateEducation);

    }

    private CandidateEducation buildCandidateEducation(CandidateEducation candidateEducationFromRequest, Candidate candidate) {
        return CandidateEducation.builder()
                .institution(candidateEducationFromRequest.getInstitution())
                .degree(candidateEducationFromRequest.getDegree())
                .fieldOfStudy(candidateEducationFromRequest.getFieldOfStudy())
                .fromDate(candidateEducationFromRequest.getFromDate())
                .toDate(candidateEducationFromRequest.getToDate())
                .candidateId(candidate.getCandidateId())
                .build();
    }

    public void updateCandidateEducation(CandidateEducation candidateEducation, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer loggedInCandidateId = candidate.getCandidateId();

        if (!candidateEducation.getCandidateId().equals(loggedInCandidateId)){
            throw new AccessDeniedException("Unauthorized access.");
        }

        candidateEducationDAO.updateCandidateEducation(candidateEducation);
    }

    public void deleteCandidateEducationById(Integer candidateEducationId) {

        candidateEducationDAO.deleteById(candidateEducationId);


    }
}
