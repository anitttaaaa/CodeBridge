package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.time.LocalDate;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateExperienceDTO {

    private Integer candidateExperienceId;
    private String companyName;
    private String candidatePosition;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer candidateId;
}
