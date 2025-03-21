package pl.zajavka.CodeBridge.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.zajavka.CodeBridge.api.dto.CandidateCVDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCVMapper;
import pl.zajavka.CodeBridge.business.CandidateCVService;
import pl.zajavka.CodeBridge.business.CandidateService;
import pl.zajavka.CodeBridge.domain.Candidate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

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
