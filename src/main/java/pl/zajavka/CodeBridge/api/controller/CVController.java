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
import pl.zajavka.CodeBridge.business.CandidateService;
import pl.zajavka.CodeBridge.domain.Candidate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@Controller
public class CVController {

    private final CandidateCVMapper candidateCVMapper;
    private final CandidateService candidateService;
    private final TemplateEngine templateEngine;

    private static final String SHOW_CANDIDATE_CV = "/candidate-portal/generate-cv";
    private static final String CANDIDATE_GENERATE_PDF = "/candidate-portal/generate-pdf";

    public CVController(CandidateCVMapper candidateCVMapper,
                        CandidateService candidateService,
                        TemplateEngine templateEngine) {
        this.candidateCVMapper = candidateCVMapper;
        this.candidateService = candidateService;
        this.templateEngine = templateEngine;
    }

    @GetMapping(SHOW_CANDIDATE_CV)
    public String generateCv(Model model) {
        Candidate candidate = candidateService.findLoggedInCandidate();

        CandidateCVDTO cvDetails = candidateCVMapper.mapToDto(candidate);

        if (cvDetails.getProfilePhoto() != null) {
            String encodedImage = Base64.getEncoder().encodeToString(cvDetails.getProfilePhoto());
            model.addAttribute("encodedImage", encodedImage);
        }

        model.addAttribute("candidateCv", cvDetails);

        return "cv";
    }


    @GetMapping(CANDIDATE_GENERATE_PDF)
    public void generatePdf(HttpServletResponse response) throws IOException {
        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateCVDTO cvDetails = candidateCVMapper.mapToDto(candidate);
        String htmlContent = generateHtmlContent(cvDetails);

        generatePdfFromHtml(htmlContent, response);
    }

    private String generateHtmlContent(CandidateCVDTO cvDetails) {
        Context context = new Context();
        context.setVariable("candidateCv", cvDetails);
        return templateEngine.process("cv", context);
    }

    private void generatePdfFromHtml(String htmlContent, HttpServletResponse response) throws IOException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=CV.pdf");

        OutputStream outputStream = response.getOutputStream();
        renderer.createPDF(outputStream);
        outputStream.close();
    }

}
