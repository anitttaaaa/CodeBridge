package pl.zajavka.CodeBridge.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zajavka.CodeBridge.api.dto.CandidatePortalDTO;
import pl.zajavka.CodeBridge.business.CandidateService;
import pl.zajavka.CodeBridge.domain.Candidate;

@Controller
@RequiredArgsConstructor
public class CandidatePortalController {

    private static final String CANDIDATE_PORTAL = "/candidate-portal";
    private final CandidateService candidateService;

    @GetMapping(CANDIDATE_PORTAL)
    public String getCandidateDetails(@RequestParam(required = false) String email, Model model) {
        Candidate candidate = candidateService.findLoggedInCandidate();

        // Tworzymy DTO i dodajemy go do modelu
        CandidatePortalDTO candidateDTO = CandidatePortalDTO.builder()
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .build();
        model.addAttribute("candidate", candidateDTO);

        return "candidate_portal";
    }
}
