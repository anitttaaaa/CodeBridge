package pl.zajavka.CodeBridge.domain;


import lombok.*;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "title")
@ToString(of = {"title"})
public class JobOffer {


    Integer jobOfferId;
    String title;
    String description;
//    List<String> jobOfferCategories;
    Employer employer;

}
//    String companyName;

//    List<String> mustHave;
//    List<String> niceToHave;
//    String salary;
//    String location;


