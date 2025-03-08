package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.zajavka.CodeBridge.business.dao.CandidateCourseDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class CandidateCourseService {

    private final CandidateService candidateService;
    private final CandidateCourseDAO candidateCourseDAO;

    public void createCourseData(CandidateCourse candidateCourseFromRequest) {


        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateCourse candidateCourse = buildCandidateCourse(candidateCourseFromRequest, candidate);
        candidateCourseDAO.createCourse(candidateCourse);

    }

    private CandidateCourse buildCandidateCourse(CandidateCourse candidateCourseFromRequest, Candidate candidate) {
        if (candidateCourseFromRequest == null || candidate == null) {
            return null;
        }

        return new CandidateCourse(
                candidateCourseFromRequest.getCandidateCourseId(),  // Zakładam, że 'CandidateCourseId' jest opcjonalne lub generowane po stronie bazy danych
                candidateCourseFromRequest.getInstitution(),
                candidateCourseFromRequest.getCourseTitle(),
                candidateCourseFromRequest.getDescription(),
                candidateCourseFromRequest.getTechnologies(),
                candidateCourseFromRequest.getFromDate(),
                candidateCourseFromRequest.getToDate(),
                candidate.getCandidateId()  // Zakładam, że 'candidate.getCandidateId()' jest wymagany
        );
    }


    public void updateCandidateCourse(CandidateCourse candidateCourse, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer loggedInCandidateId = candidate.getCandidateId();

        if (!candidateCourse.getCandidateId().equals(loggedInCandidateId)){
            throw new AccessDeniedException("Unauthorized access.");
        }

        candidateCourseDAO.updateCandidateCourse(candidateCourse);
    }


    public void deleteCandidateCourseById(Integer candidateCourseId) {
        candidateCourseDAO.deleteById(candidateCourseId);
    }

}
