package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {

    private Integer applicationId;
    private Integer jobOfferId;
    private Integer employerId;
    private Integer candidateId;
    private String applicationStatus;


}
