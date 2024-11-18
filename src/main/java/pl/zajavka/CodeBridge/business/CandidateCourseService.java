package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.domain.CandidateProject;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateCourseService {

    private final CandidateService candidateService;

    @Transactional
    public void createCourseData(List<CandidateCourse> candidateCoursesFromRequest, Authentication authentication) {
        Candidate candidate = candidateService.findLoggedInCandidate();
        // Iteracja przez listę doświadczeń z requestu i dodanie ich do kandydata
        List<CandidateCourse> candidateCourses = candidate.getCandidateCourses();

        for (CandidateCourse courseFromRequest : candidateCoursesFromRequest) {
            CandidateCourse candidateCourse = buildCandidateCourse(courseFromRequest);
            candidateCourses.add(candidateCourse);
        }
        Candidate candidateWithCourse = candidate.withCandidateCourses(candidateCourses);

        candidateService.createCandidateExperience(candidateWithCourse);
    }

    private CandidateCourse buildCandidateCourse(CandidateCourse candidateCourseFromRequest) {
        return CandidateCourse.builder()
                .candidateCoursesId(candidateCourseFromRequest.getCandidateCoursesId())
                .institution(candidateCourseFromRequest.getCourseTitle())
                .courseTitle(candidateCourseFromRequest.getDescription())
                .description(candidateCourseFromRequest.getTechnologies())
                .technologies(candidateCourseFromRequest.getTechnologies())
                .fromDate(candidateCourseFromRequest.getFromDate())
                .toDate(candidateCourseFromRequest.getToDate())
                .build();
    }
}
