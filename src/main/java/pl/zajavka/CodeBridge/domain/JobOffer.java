package pl.zajavka.CodeBridge.domain;


import lombok.*;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "jobOfferTitle")
@ToString(of = {"jobOfferTitle"})
public class JobOffer {


    Integer jobOfferId;
    String jobOfferTitle;
    String description;
    String techSpecialization;
    Employer employer;
    String workType;
    String city;
    String experience;
    String salary;
    List<String> mustHaveSkills;
    List<String> niceToHaveSkills;


}


