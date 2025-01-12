package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.zajavka.CodeBridge.business.JobApplicationService;


@Controller
@RequiredArgsConstructor
public class JobApplicationController {

    private static final String APPLY_FOR_A_JOB = "/apply/{jobOfferId}";

    private final JobApplicationService jobApplicationService;

    @PostMapping(APPLY_FOR_A_JOB)
    public String applyForJob(
            @PathVariable Integer jobOfferId,
            Authentication authentication) {
        jobApplicationService.applyForJob(jobOfferId, authentication);

        // Przekierowanie na stronę z ofertami pracy lub stronę główną
        return "redirect:/candidate-applications";    }
}




