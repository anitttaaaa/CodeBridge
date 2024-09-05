package pl.zajavka.CodeBridge.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class JobOfferAdd {


    String jobOfferTitle;
    String description;
//    String techSpecialization;
}
