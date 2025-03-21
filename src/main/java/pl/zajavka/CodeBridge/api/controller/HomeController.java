package pl.zajavka.CodeBridge.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.enums.*;
import pl.zajavka.CodeBridge.business.JobOfferService;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private static final String GET_ALL_JOB_OFFERS = "/";
    private static final String GET_FILTERED_JOB_OFFERS = "/filtered-job-offers";

    private final JobOfferService jobOfferService;

    public HomeController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping(GET_ALL_JOB_OFFERS)
    public String getAllJobOffers(Model model) {

        List<JobOfferDTO> jobOffersDTO = jobOfferService.getAllJobOffersSorted();

        model.addAttribute("jobOffers", jobOffersDTO);

        return "home";
    }



    @GetMapping(GET_FILTERED_JOB_OFFERS)
    public String getFilteredJobOffers(
            @RequestParam(required = false) TechSpecializationsEnum techSpecialization,
            @RequestParam(required = false) WorkTypesEnum workType,
            @RequestParam(required = false) CitiesEnum city,
            @RequestParam(required = false) ExperiencesEnum experience,
            @RequestParam(required = false) SalaryEnum salary,
            Model model) {

        List<JobOfferDTO> filteredJobOffersDTO = jobOfferService.getFilteredAndSortedJobOffers(
                techSpecialization, workType, city, experience, salary);

        model.addAttribute("jobOffers", filteredJobOffersDTO);

        return "home";
    }



}
