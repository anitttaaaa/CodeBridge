package pl.zajavka.CodeBridge.business;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.zajavka.CodeBridge.api.dto.CandidateCVDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCVMapper;
import pl.zajavka.CodeBridge.domain.Candidate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@Service
public class CandidateCVService {

    private final CandidateService candidateService;
    private final CandidateCVMapper candidateCVMapper;
    private final ThymeleafViewResolver thymeleafViewResolver;


    @Autowired
    public CandidateCVService(CandidateService candidateService, CandidateCVMapper candidateCVMapper, ThymeleafViewResolver thymeleafViewResolver) {
        this.candidateService = candidateService;
        this.candidateCVMapper = candidateCVMapper;
        this.thymeleafViewResolver = thymeleafViewResolver;
    }

    public CandidateCVDTO generateCandidateCV() {
        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateCVDTO cvDetails = candidateCVMapper.mapToDto(candidate);

        if (cvDetails.getProfilePhoto() != null) {
            String encodedImage = Base64.getEncoder().encodeToString(cvDetails.getProfilePhoto());

            cvDetails = new CandidateCVDTO.Builder()
                    .profilePhoto(cvDetails.getProfilePhoto())
                    .candidateId(cvDetails.getCandidateId())
                    .name(cvDetails.getName())
                    .surname(cvDetails.getSurname())
                    .email(cvDetails.getEmail())
                    .phone(cvDetails.getPhone())
                    .linkedIn(cvDetails.getLinkedIn())
                    .gitHub(cvDetails.getGitHub())
                    .techSpecialization(cvDetails.getTechSpecialization())
                    .candidateSkills(cvDetails.getCandidateSkills())
                    .candidateExperiences(cvDetails.getCandidateExperiences())
                    .candidateProjects(cvDetails.getCandidateProjects())
                    .candidateEducationStages(cvDetails.getCandidateEducationStages())
                    .candidateCourses(cvDetails.getCandidateCourses())
                    .hobby(cvDetails.getHobby())
                    .userId(cvDetails.getUserId())
                    .encodedImage(cvDetails.getEncodedImage())
                    .build();

        }
        return cvDetails;
    }
    public void generateCandidateCVPdf(HttpServletResponse response) throws IOException {
        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateCVDTO cvDetails = candidateCVMapper.mapToDto(candidate);
        String htmlContent = generateHtmlContent(cvDetails);

        generatePdfFromHtml(htmlContent, response);
    }

    private String generateHtmlContent(CandidateCVDTO cvDetails) {
        Context context = new Context();
        context.setVariable("candidateCv", cvDetails);
        return thymeleafViewResolver.getTemplateEngine().process("cv", context);
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

