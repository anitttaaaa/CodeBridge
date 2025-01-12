package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class CandidateApplicationsController {

    private static final String CANDIDATE_JOB_APPLICATIONS = "/candidate_applications";


    @GetMapping("/candidate-applications")
    public String candidateApplications() {
        return "candidate_applications";
    }
}
