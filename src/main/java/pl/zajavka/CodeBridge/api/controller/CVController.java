package pl.zajavka.CodeBridge.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zajavka.CodeBridge.api.dto.CandidateCVDTO;
import pl.zajavka.CodeBridge.business.CandidateCVService;

import java.io.IOException;

@Controller
public class CVController {

    private final CandidateCVService candidateCVService;

    private static final String SHOW_CANDIDATE_CV = "/candidate-portal/generate-cv";
    private static final String CANDIDATE_GENERATE_PDF = "/candidate-portal/generate-pdf";

    public CVController(CandidateCVService candidateCVService) {
        this.candidateCVService = candidateCVService;
    }

    @GetMapping(SHOW_CANDIDATE_CV)
    public String generateCv(Model model) {

        CandidateCVDTO cvDetails = candidateCVService.generateCandidateCV();

        model.addAttribute("candidateCv", cvDetails);

        return "cv";
    }

    @GetMapping(CANDIDATE_GENERATE_PDF)
    public void generatePdf(HttpServletResponse response) throws IOException {
        candidateCVService.generateCandidateCVPdf(response);
    }

}
