package pl.zajavka.CodeBridge.api.controller;

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

import java.util.List;


@Controller
public class JobApplicationController {

    private static final String CANDIDATE_APPLY_FOR_A_JOB = "/apply/{jobOfferId}";
    private static final String CANDIDATE_GET_MY_APPLICATIONS = "/candidate-portal/candidate-applications";
    private static final String EMPLOYER_GET_APPLICATIONS_HISTORY = "/employer-portal/applications-history";
    private static final String CANDIDATE_GET_MY_APPLICATIONS_HISTORY = "/candidate-portal/applications-history";

    private static final String GET_EMPLOYER_ALL_JOB_APPLICATIONS = "/employer-portal/job-applications";
    private static final String POST_EMPLOYER_JOB_APPLICATION_ACCEPT = "/employer-portal/job-applications/accept";
    private static final String POST_EMPLOYER_JOB_APPLICATION_REJECT = "/employer-portal/job-applications/reject";


    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping(CANDIDATE_APPLY_FOR_A_JOB)
    public String applyForJob(
            @PathVariable Integer jobOfferId,
            Authentication authentication) {

        jobApplicationService.applyForJob(jobOfferId, authentication);

        return "redirect:/candidate-portal/candidate-applications";
    }

    @PostMapping(POST_EMPLOYER_JOB_APPLICATION_ACCEPT)
    public String acceptJobApplication(
            @RequestParam("applicationId") Integer applicationId) {

        jobApplicationService.acceptJobApplication(applicationId);


        return "redirect:/employer-portal/applications-history";
    }

    @PostMapping(POST_EMPLOYER_JOB_APPLICATION_REJECT)
    public String rejectJobApplication(
            @RequestParam("applicationId") Integer applicationId) {

        jobApplicationService.rejectJobApplication(applicationId);

        return "redirect:/employer-portal/applications-history";
    }

    @GetMapping(CANDIDATE_GET_MY_APPLICATIONS)
    public String showAllCandidateApplications(Authentication authentication, Model model) {

        List<JobApplicationDTO> jobApplications = jobApplicationService.getCandidateApplications(authentication);

        model.addAttribute("jobApplications", jobApplications);

        return "candidate_applications";
    }

    @GetMapping(GET_EMPLOYER_ALL_JOB_APPLICATIONS)
    public String getAllJobApplications(Authentication authentication, Model model) {

        List<JobApplicationDTO> employerJobApplications = jobApplicationService.getAllJobApplicationsByEmployerId(authentication);

        model.addAttribute("employerJobApplications", employerJobApplications);

        return "employer_portal_job_applications";
    }

    @GetMapping(EMPLOYER_GET_APPLICATIONS_HISTORY)
    public String getApplicationsHistory(Authentication authentication, Model model) {

        List<ApplicationsHistoryDTO> employerHistoryApplications = jobApplicationService.getAllEmployerHistoryJobApplications(authentication);

        model.addAttribute("employerHistoryApplications", employerHistoryApplications);

        return "employer_portal_job_applications_history";
    }

    @GetMapping(CANDIDATE_GET_MY_APPLICATIONS_HISTORY)
    public String getMyApplicationsHistory(Authentication authentication, Model model) {

        List<ApplicationsHistoryDTO> candidateHistoryApplications = jobApplicationService.getAllCandidateHistoryJobApplications(authentication);

        model.addAttribute("candidateHistoryApplications", candidateHistoryApplications);

        return "candidate_portal_job_applications_history";
    }


}





