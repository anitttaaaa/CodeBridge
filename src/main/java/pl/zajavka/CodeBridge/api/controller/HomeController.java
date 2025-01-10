package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zajavka.CodeBridge.business.JobOfferService;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private static final String SHOW_ALL_JOB_OFFERS = "/";

    private static final String SHOW_FILTERED_JOB_OFFERS = "/filtered-job-offers";

    private final JobOfferService jobOfferService;

    @GetMapping(SHOW_ALL_JOB_OFFERS)
    public String getAllJobOffers(Model model) {

        List<JobOffer> jobOffers = jobOfferService.getAllJobOffers();
        model.addAttribute("jobOffers", jobOffers);

    return "home";
    }


    @GetMapping(SHOW_FILTERED_JOB_OFFERS)
    public String getFilteredJobOffers(
            @RequestParam(required = false) String techSpecialization,
            @RequestParam(required = false) String workType,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) String salary,

            Model model) {

        // Przekazanie parametrów do serwisu
        List<JobOffer> filteredJobOffers = jobOfferService.getFilteredJobOffers(
                techSpecialization, workType, city, experience, salary
        );

        // Dodanie wyniku do modelu
        model.addAttribute("jobOffers", filteredJobOffers);

        return "home";
    }


}
