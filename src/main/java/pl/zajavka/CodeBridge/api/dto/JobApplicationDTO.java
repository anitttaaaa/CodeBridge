package pl.zajavka.CodeBridge.api.dto;

import lombok.*;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {

    private Integer applicationId;

    private JobOfferDTO jobOffer;
    private EmployerDTO employer;
    private CandidateDTO candidate;
    private ApplicationStatus applicationStatus;


}
