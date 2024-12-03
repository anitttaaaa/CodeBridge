package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.time.LocalDate;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateCourseId")
@ToString(of = {"candidateCourseId", "institution", "courseTitle"})
public class CandidateCourse {

    Integer candidateCourseId;
    String institution;
    String courseTitle;
    String description;
    String technologies;
    LocalDate fromDate;
    LocalDate toDate;
    Integer candidateId;
}

