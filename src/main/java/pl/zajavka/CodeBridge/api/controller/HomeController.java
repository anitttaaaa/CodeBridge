package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zajavka.CodeBridge.business.JobOfferService;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private static final String GET_ALL_JOB_OFFERS = "/";
    private static final String GET_FILTERED_JOB_OFFERS = "/filtered-job-offers";

    private final JobOfferService jobOfferService;

    @GetMapping(GET_ALL_JOB_OFFERS)
    public String getAllJobOffers(Model model) {

        List<JobOffer> jobOffers = jobOfferService.getAllJobOffers().stream()
                .sorted(Comparator.comparingInt(JobOffer::getJobOfferId).reversed())
                .collect(Collectors.toList());

        model.addAttribute("jobOffers", jobOffers);

        return "home";
    }


    @GetMapping(GET_FILTERED_JOB_OFFERS)
    public String getFilteredJobOffers(
            @RequestParam(required = false) String techSpecialization,
            @RequestParam(required = false) String workType,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) String salary,
            Model model) {

        if (techSpecialization != null && techSpecialization.isEmpty()) {
            techSpecialization = null;
        }
        if (workType != null && workType.isEmpty()) {
            workType = null;
        }
        if (city != null && city.isEmpty()) {
            city = null;
        }
        if (experience != null && experience.isEmpty()) {
            experience = null;
        }
        if (salary != null && salary.isEmpty()) {
            salary = null;
        }

        List<JobOffer> filteredJobOffers = jobOfferService.getFilteredJobOffers(
                        techSpecialization, workType, city, experience, salary)
                .stream()
                .sorted(Comparator.comparingInt(JobOffer::getJobOfferId).reversed())
                .collect(Collectors.toList());


        model.addAttribute("jobOffers", filteredJobOffers);

        return "home";
    }


}
