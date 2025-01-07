package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zajavka.CodeBridge.business.JobOfferService;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobOfferController {


    private static final String SHOW_ALL_JOB_OFFERS = "/";

    private final JobOfferService jobOfferService;

    @GetMapping(SHOW_ALL_JOB_OFFERS)
    public String getAllJobOffers(Model model) {
        System.out.println("Inside JobOfferController.getAllJobOffers()"); // Log startu metody

        List<JobOffer> jobOffers = jobOfferService.getAllJobOffers();
        System.out.println("Job Offers:: " + jobOffers);

        model.addAttribute("jobOffers", jobOffers);

    return "home";
    }
}
