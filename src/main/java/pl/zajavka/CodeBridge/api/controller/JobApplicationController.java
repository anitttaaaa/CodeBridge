package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.business.JobApplicationService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class JobApplicationController {

    private static final String APPLY_FOR_A_JOB = "/apply/{jobOfferId}";
    private static final String GET_MY_APPLICATIONS = "/candidate-applications";
    private static final String GET_APPLICATIONS_HISTORY = "/employer-portal/applications-history";

    private final JobApplicationService jobApplicationService;


    @PostMapping(APPLY_FOR_A_JOB)
    public String applyForJob(
            @PathVariable Integer jobOfferId,
            Authentication authentication) {
        jobApplicationService.applyForJob(jobOfferId, authentication);

        // Przekierowanie na stronę z ofertami pracy lub stronę główną
        return "redirect:/candidate-applications";
    }

    @GetMapping(GET_MY_APPLICATIONS)
    public String showAllCandidateApplications(Authentication authentication, Model model) {

        List<JobApplicationDTO> jobApplications = jobApplicationService.getCandidateApplications(authentication).stream()
                .sorted(Comparator.comparingInt(JobApplicationDTO::getApplicationId).reversed())
                .collect(Collectors.toList());

        model.addAttribute("jobApplications", jobApplications);

        return "candidate_applications";  // Ścieżka do pliku HTML
    }

    @GetMapping(GET_APPLICATIONS_HISTORY)
    public String getApplicationsHistory() {
        return "/employer_portal_job_applications_history";
    }


}





