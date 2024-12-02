package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.time.LocalDate;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateCoursesId")
@ToString(of = {"candidateCoursesId", "institution", "courseTitle"})
public class CandidateCourse {

    Integer candidateCoursesId;
    String institution;
    String courseTitle;
    String description;
    String technologies;
    LocalDate fromDate;
    LocalDate toDate;
    Integer candidateId;
}

