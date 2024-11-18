package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.time.LocalDate;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEducationDTO {

    private Integer candidateEducationId;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private LocalDate fromDate;
    private LocalDate toDate;

}
