package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProjectDTO {

    private Integer candidateProjectId;
    private String projectTitle;
    private String description;
    private List<String> technologies;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String projectLink;
}
