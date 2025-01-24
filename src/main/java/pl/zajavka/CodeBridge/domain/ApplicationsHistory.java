package pl.zajavka.CodeBridge.domain;

import lombok.*;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

@With
@Value
@Builder
@EqualsAndHashCode(of = "applicationHistoryId")
@ToString(of = {"applicationHistoryId"})
public class ApplicationsHistory
{
    Integer applicationHistoryId;
    JobOffer jobOffer;
    Employer employer;
    Candidate candidate;
    ApplicationStatus applicationStatus;

}
