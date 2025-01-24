package pl.zajavka.CodeBridge.domain;

import lombok.*;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

@With
@Value
@Builder
@EqualsAndHashCode(of = "applicationId")
@ToString(of = {"applicationId", "jobOffer", "employer", "candidate", "applicationStatus"})
public class JobApplication {

    Integer applicationId;
    JobOffer jobOffer;
    Employer employer;
    Candidate candidate;
    ApplicationStatus applicationStatus;

}
