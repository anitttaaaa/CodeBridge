package pl.zajavka.CodeBridge.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CVController {

    private final CandidateCVMapper candidateCVMapper;
    private final CandidateService candidateService;
    private final TemplateEngine templateEngine;

    private static final String SHOW_CANDIDATE_CV = "/candidate-portal/generate-cv";
    private static final String CANDIDATE_GENERATE_PDF = "/candidate-portal/generate-pdf";


    @GetMapping(SHOW_CANDIDATE_CV)
    public String generateCv(Model model) {
        // Pobierz dane kandydata
        Candidate candidate = candidateService.findLoggedInCandidate();

        // Mapowanie kandydata na DTO
        CandidateCVDTO cvDetails = candidateCVMapper.mapToDto(candidate);

        // Sprawdź, czy profilowe zdjęcie jest dostępne
        if (cvDetails.getProfilePhoto() != null) {
            // Zakoduj zdjęcie w Base64
            String encodedImage = Base64.getEncoder().encodeToString(cvDetails.getProfilePhoto());
            // Dodaj zakodowane zdjęcie do modelu
            model.addAttribute("encodedImage", encodedImage);
        }

        // Dodaj pozostałe dane do modelu
        model.addAttribute("candidateCv", cvDetails);

        // Zwróć widok z HTML do wyświetlenia
        return "cv";  // Nazwa szablonu Thymeleaf (plik cv.html)
    }


    // Endpoint do generowania PDF
    @GetMapping(CANDIDATE_GENERATE_PDF)
    public void generatePdf(HttpServletResponse response) throws IOException {
        // Pobierz dane kandydata
        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateCVDTO cvDetails = candidateCVMapper.mapToDto(candidate);

        // Renderuj HTML z Thymeleaf
        String htmlContent = generateHtmlContent(cvDetails);

        // Konwertuj HTML na PDF
        generatePdfFromHtml(htmlContent, response);
    }

    // Metoda do generowania HTML z Thymeleaf
    private String generateHtmlContent(CandidateCVDTO cvDetails) {
        Context context = new Context();
        context.setVariable("candidateCv", cvDetails);
        return templateEngine.process("cv", context);
    }

    // Metoda do konwersji HTML na PDF
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
