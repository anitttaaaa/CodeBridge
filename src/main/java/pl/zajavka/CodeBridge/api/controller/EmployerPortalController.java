package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zajavka.CodeBridge.api.dto.JobOfferAddRequestDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.JobOfferMapper;
import pl.zajavka.CodeBridge.business.JobOfferAddService;
import pl.zajavka.CodeBridge.domain.JobOfferFromRequest;


@Controller
@RequiredArgsConstructor
public class EmployerPortalController {

    private static final String EMPLOYER = "/employer-portal";
    private static final String EMPLOYER_NEW_JOB_OFFER = "/employer-portal/new-job-offer";
    private static final String EMPLOYER_NEW_JOB_OFFER_ADD = "/employer-portal/new-job-offer/add";

    private final JobOfferMapper jobOfferMapper;
    private final JobOfferAddService jobOfferAddService;



    @GetMapping(value = EMPLOYER)
    public String employerPortal() {return "employer_portal";}


    @GetMapping(value = EMPLOYER_NEW_JOB_OFFER)
    public String showJobOfferForm(Model model) {
        model.addAttribute("jobOfferAddRequestDTO", new JobOfferAddRequestDTO());
        return "employer_portal_new_job_offer";
    }

    @PostMapping(EMPLOYER_NEW_JOB_OFFER_ADD)
    public String addJobOffer(
            @ModelAttribute("jobOfferAddRequestDTO") JobOfferAddRequestDTO jobOfferAddRequestDTO,
            Authentication authentication)
    {

        JobOfferFromRequest request = jobOfferMapper.mapFromDTO(jobOfferAddRequestDTO);
        jobOfferAddService.createJobOfferData(request, authentication);


        return "redirect:/employer-portal";
    }


}


