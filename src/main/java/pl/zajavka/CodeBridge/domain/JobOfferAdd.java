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
//    List<String> jobOfferCategories;
}
