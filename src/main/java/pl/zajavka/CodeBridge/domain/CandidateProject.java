package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateProjectId")
@ToString(of = {"candidateProjectId", "projectTitle"})
public class CandidateProject {

    Integer candidateProjectId;
    String projectTitle;
    String description;
    List<String> technologies;
    LocalDate fromDate;
    LocalDate toDate;
    String projectLink;
}
