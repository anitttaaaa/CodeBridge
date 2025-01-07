package pl.zajavka.CodeBridge.api.dto;

import lombok.*;
import pl.zajavka.CodeBridge.domain.Employer;

import java.util.List;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDTO {

    private Integer jobOfferId;

    private String jobOfferTitle;
    private String description;
    private String techSpecialization;
    private String workType;
    private String city;
    private String experience;
    private String salary;
    private List<String> mustHaveSkills;
    private List<String> niceToHaveSkills;

    private Employer employer;

}








