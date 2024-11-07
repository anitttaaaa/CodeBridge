package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.time.LocalDate;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateExperienceId")
@ToString(of = {"candidateExperienceId", "companyName", "position"})
public class CandidateExperience {

    Integer candidateExperienceId;
    String companyName;
    String position;
    String description;
    LocalDate fromDate;
    LocalDate toDate;
}
