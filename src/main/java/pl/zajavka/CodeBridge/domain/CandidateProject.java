package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.time.LocalDate;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateProjectId")
@ToString(of = {"candidateProjectId", "projectTitle"})
public class CandidateProject {

    Integer candidateProjectId;
    String projectTitle;
    String technologies;
    String description;
    LocalDate fromDate;
    LocalDate toDate;
    String projectLink;
    Integer candidateId;
}
