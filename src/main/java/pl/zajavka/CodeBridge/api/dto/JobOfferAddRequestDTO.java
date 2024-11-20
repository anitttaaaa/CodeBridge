package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.util.List;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferAddRequestDTO {


    private String jobOfferTitle;
    private String description;
    private String techSpecialization;
    private String workType;
    private String city;
    private String experience;
    private String salary;
    private List<String> mustHaveSkills;
    private List<String> niceToHaveSkills;

}

//    private List<String> categories;
//    private List<String> mustHave;
//    private List<String> niceToHave;
//    private String salary;
//    private String location;






