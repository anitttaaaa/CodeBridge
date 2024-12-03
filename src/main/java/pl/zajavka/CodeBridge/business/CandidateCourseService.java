package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.CodeBridge.business.dao.CandidateCourseDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

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
        return CandidateCourse.builder()
                .institution(candidateCourseFromRequest.getInstitution())
                .courseTitle(candidateCourseFromRequest.getCourseTitle())
                .description(candidateCourseFromRequest.getDescription())
                .technologies(candidateCourseFromRequest.getTechnologies())
                .fromDate(candidateCourseFromRequest.getFromDate())
                .toDate(candidateCourseFromRequest.getToDate())
                .candidateId(candidate.getCandidateId())
                .build();
    }
}
