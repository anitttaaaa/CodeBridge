package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateCourse;

public interface CandidateCourseDAO {

    CandidateCourse createCourse(CandidateCourse candidateCourse, Integer candidateId);

    void updateCandidateCourse(CandidateCourse candidateCourse, Integer candidateId);
    void deleteById(Integer candidateCourseId);
}
