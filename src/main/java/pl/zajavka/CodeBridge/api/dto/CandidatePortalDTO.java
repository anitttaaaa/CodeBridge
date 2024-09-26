package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePortalDTO {


    private Integer candidateId;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String bio;
    private Integer userId;
}
