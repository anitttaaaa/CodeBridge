package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zajavka.CodeBridge.api.dto.ApplicationsHistoryDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.business.JobApplicationService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class JobApplicationController {

    private static final String CANDIDATE_APPLY_FOR_A_JOB = "/apply/{jobOfferId}";
    private static final String CANDIDATE_GET_MY_APPLICATIONS = "/candidate-applications";
    private static final String EMPLOYER_GET_APPLICATIONS_HISTORY = "/employer-portal/applications-history";
    private static final String CANDIDATE_GET_MY_APPLICATIONS_HISTORY = "/candidate-portal/applications-history";

    private static final String GET_EMPLOYER_ALL_JOB_APPLICATIONS = "/employer-portal/job-applications";
    private static final String POST_EMPLOYER_JOB_APPLICATION_ACCEPT = "/employer-portal/job_applications/accept";


    private final JobApplicationService jobApplicationService;


    @PostMapping(POST_EMPLOYER_JOB_APPLICATION_ACCEPT)
    public String acceptJobApplication(
            @RequestParam("applicationId") Integer applicationId,
            Authentication authentication) {

        jobApplicationService.acceptJobApplication(applicationId, authentication);

        return "redirect:/employer-portal/applications-history";
    }

    @PostMapping(CANDIDATE_APPLY_FOR_A_JOB)
    public String applyForJob(
            @PathVariable Integer jobOfferId,
            Authentication authentication) {
        jobApplicationService.applyForJob(jobOfferId, authentication);

        // Przekierowanie na stronę z ofertami pracy lub stronę główną
        return "redirect:/candidate-applications";
    }

    @GetMapping(CANDIDATE_GET_MY_APPLICATIONS)
    public String showAllCandidateApplications(Authentication authentication, Model model) {

        List<JobApplicationDTO> jobApplications = jobApplicationService.getCandidateApplications(authentication).stream()
                .sorted(Comparator.comparingInt(JobApplicationDTO::getApplicationId).reversed())
                .collect(Collectors.toList());

        model.addAttribute("jobApplications", jobApplications);

        return "candidate_applications";  // Ścieżka do pliku HTML
    }

    @GetMapping(GET_EMPLOYER_ALL_JOB_APPLICATIONS)
    public String getAllJobApplications(
            Authentication authentication,
            Model model) {

        List<JobApplicationDTO> employerJobApplications = jobApplicationService.getAllJobApplicationsByEmployerId(authentication);

        model.addAttribute("employerJobApplications", employerJobApplications);



        return "employer_portal_job_applications";
    }
    @GetMapping(EMPLOYER_GET_APPLICATIONS_HISTORY)
    public String getApplicationsHistory(
            Authentication authentication,
            Model model) {

        System.out.println("hejaaaa");

        List<ApplicationsHistoryDTO> employerHistoryApplications = jobApplicationService.getAllHistoryJobApplications(authentication);
        model.addAttribute("employerHistoryApplications", employerHistoryApplications);


        return "/employer_portal_job_applications_history";
    }

 @GetMapping(CANDIDATE_GET_MY_APPLICATIONS_HISTORY)
    public String getMyApplicationsHistory(
            Authentication authentication,
            Model model) {


        return "/candidate_portal_job_applications_history";
    }


}





