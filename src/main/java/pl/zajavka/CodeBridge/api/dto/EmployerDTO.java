package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.util.Set;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDTO {

    private Integer employerId;
    private String companyName;
    private String email;
    private String nip;
    private Integer userId;

    private Set<JobOfferDTO> jobOffers;

}
