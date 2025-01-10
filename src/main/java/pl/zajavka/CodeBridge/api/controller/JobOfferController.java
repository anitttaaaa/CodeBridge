package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class JobOfferController {


    @GetMapping("/apply")
    public String applyForJob(Model model, Principal principal) {
        if (principal == null) {
            // Jeśli użytkownik nie jest zalogowany, przekieruj go do logowania
            return "redirect:/login";
        }

        // Jeśli użytkownik jest zalogowany, sprawdź rolę
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isCandidate = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CANDIDATE"));

        if (!isCandidate) {
            // Jeśli użytkownik nie ma roli CANDIDATE, wyświetl komunikat
            model.addAttribute("errorMessage", "Only candidates can apply for jobs. Please log in as a candidate to proceed.");
            return "errorPage";  // Strona z komunikatem
        }

        // Jeśli użytkownik jest kandydatem, pozwól mu aplikować
        // (np. przekierowanie do strony aplikacji)
        return "apply";
    }
}

