package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.business.CandidateService;
import pl.zajavka.CodeBridge.business.JobApplicationService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class JobApplicationController {

    private static final String APPLY_FOR_A_JOB = "/apply/{jobOfferId}";

    private final JobApplicationService jobApplicationService;
    private final CandidateService candidateService;

    @PostMapping(APPLY_FOR_A_JOB)
    public String applyForJob(
            @PathVariable Integer jobOfferId,
            Authentication authentication) {
        jobApplicationService.applyForJob(jobOfferId, authentication);

        // Przekierowanie na stronę z ofertami pracy lub stronę główną
        return "redirect:/candidate-applications";
    }

    @GetMapping("/candidate-applications")
    public String showAllCandidateApplications(Authentication authentication, Model model) {
        // Pobierz aplikacje kandydata


        List<JobApplicationDTO> jobApplications = jobApplicationService.getCandidateApplications(authentication);

        // Dodaj listę aplikacji do modelu
        model.addAttribute("jobApplications", jobApplications);

        // Zwróć nazwę widoku, który ma być renderowany
        return "candidate_applications";  // Ścieżka do pliku HTML
    }

}





