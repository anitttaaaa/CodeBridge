package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmployerPortalController {

    private static final String EMPLOYER = "/employer-portal";
    private static final String EMPLOYER_NEW_JOB_OFFER = "/employer-portal/new-job-offer";


    @GetMapping(value = EMPLOYER)
    public String employerPortal() {return "employer_portal";}


    @GetMapping(value = EMPLOYER_NEW_JOB_OFFER)
    public String employerPortalNewJobOffer() {return "employer_portal_new_job_offer";}



}


