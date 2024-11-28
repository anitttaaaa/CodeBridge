package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.time.LocalDate;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProjectDTO {

    private Integer candidateProjectId;
    private String projectTitle;
    private String technologies;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String projectLink;
    private Integer candidateId;

}
