package pl.zajavka.CodeBridge.api.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
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
//    private String techSpecialization;

}

//    private List<String> categories;
//    private List<String> mustHave;
//    private List<String> niceToHave;
//    private String salary;
//    private String location;






