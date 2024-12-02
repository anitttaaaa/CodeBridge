package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.time.LocalDate;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateEducationId")
@ToString(of = {"candidateEducationId", "institution", "degree", "fieldOfStudy"})
public class CandidateEducation {

    Integer candidateEducationId;
    String institution;
    String degree;
    String fieldOfStudy;
    LocalDate fromDate;
    LocalDate toDate;
    Integer candidateId;
}
