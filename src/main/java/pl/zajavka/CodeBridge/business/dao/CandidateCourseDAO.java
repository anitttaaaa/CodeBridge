package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateCourse;

public interface CandidateCourseDAO {

    CandidateCourse createCourse(CandidateCourse candidateCourse);

    void updateCandidateCourse(CandidateCourse candidateCourse);
    void deleteById(Integer candidateCourseId);
}
