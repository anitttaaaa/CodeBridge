package pl.zajavka.CodeBridge.api.dto;

import lombok.*;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.time.LocalDate;
import java.util.List;

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

}
