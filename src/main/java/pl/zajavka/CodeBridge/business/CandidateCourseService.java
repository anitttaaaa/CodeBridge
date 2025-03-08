package pl.zajavka.CodeBridge.business;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.zajavka.CodeBridge.business.dao.CandidateCourseDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateCourse;

import java.nio.file.AccessDeniedException;

@Service
public class CandidateCourseService {

    private final CandidateService candidateService;
    private final CandidateCourseDAO candidateCourseDAO;

    public CandidateCourseService(CandidateService candidateService,
                                  CandidateCourseDAO candidateCourseDAO) {
        this.candidateService = candidateService;
        this.candidateCourseDAO = candidateCourseDAO;
    }

    public void createCourseData(CandidateCourse candidateCourseFromRequest) {


        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateCourse candidateCourse = buildCandidateCourse(candidateCourseFromRequest, candidate);
        candidateCourseDAO.createCourse(candidateCourse);

    }

    private CandidateCourse buildCandidateCourse(CandidateCourse candidateCourseFromRequest, Candidate candidate) {
        if (candidateCourseFromRequest == null || candidate == null) {
            return null;
        }

        return new CandidateCourse.Builder()
                .candidateCourseId(candidateCourseFromRequest.getCandidateCourseId())  // Jeśli 'CandidateCourseId' jest opcjonalne
                .institution(candidateCourseFromRequest.getInstitution())
                .courseTitle(candidateCourseFromRequest.getCourseTitle())
                .description(candidateCourseFromRequest.getDescription())
                .technologies(candidateCourseFromRequest.getTechnologies())
                .fromDate(candidateCourseFromRequest.getFromDate())
                .toDate(candidateCourseFromRequest.getToDate())
                .candidateId(candidate.getCandidateId())  // Przypisujemy 'candidateId' z obiektu 'candidate'
                .build();
    }



    public void updateCandidateCourse(CandidateCourse candidateCourse, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer loggedInCandidateId = candidate.getCandidateId();

        if (!candidateCourse.getCandidateId().equals(loggedInCandidateId)) {
            throw new AccessDeniedException("Unauthorized access.");
        }

        candidateCourseDAO.updateCandidateCourse(candidateCourse);
    }


    public void deleteCandidateCourseById(Integer candidateCourseId) {
        candidateCourseDAO.deleteById(candidateCourseId);
    }

}
