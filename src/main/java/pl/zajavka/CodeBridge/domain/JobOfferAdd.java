package pl.zajavka.CodeBridge.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@Value
@Builder
public class JobOfferAdd {


    String jobOfferTitle;
    String description;
    String techSpecialization;
    String workType;
    String city;
    String experience;
    String salary;
    List<String> mustHaveSkills;
    List<String> niceToHaveSkills;

}
