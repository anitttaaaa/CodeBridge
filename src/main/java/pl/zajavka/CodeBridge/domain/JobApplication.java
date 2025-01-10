package pl.zajavka.CodeBridge.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "employerId")
@ToString(of = {"applicationId", "jobOfferId"})
public class JobApplication {

    Integer applicationId;
    Integer jobOfferId;
    Integer employerId;
    Integer candidateId;
    String applicationStatus;

}
