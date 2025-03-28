package pl.zajavka.CodeBridge.business;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.zajavka.CodeBridge.api.dto.CandidateCVDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCVMapper;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;
import pl.zajavka.CodeBridge.domain.Candidate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateCVServiceTest {

    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateCVMapper candidateCVMapper;

    @InjectMocks
    private CandidateCVService candidateCVService;

    @Mock
    private HttpServletResponse response;


    @Mock
    private ITextRenderer renderer;

    @Mock
    private OutputStream outputStream;


    private Candidate candidate;
    private CandidateCVDTO expectedCvDetails;

    @BeforeEach
    void setUp() {

        candidate = new Candidate.Builder()
                .profilePhoto(null)
                .candidateId(1)
                .name("Aleksandra")
                .surname("Bobrowska")
                .email("a.bobrosta@test.com")
                .phone("222333444")
                .linkedIn("https://linkedin.com/in/abobr")
                .gitHub("https://github.com/abobr")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .candidateSkills(new ArrayList<>())
                .candidateExperiences(new ArrayList<>())
                .candidateProjects(new ArrayList<>())
                .candidateEducationStages(new ArrayList<>())
                .candidateCourses(new ArrayList<>())
                .hobby("Hobby")
                .userId(1)
                .build();

        expectedCvDetails = new CandidateCVDTO.Builder()
                .profilePhoto(null)
                .candidateId(1)
                .name("Aleksandra")
                .surname("Bobrowska")
                .email("a.bobrosta@test.com")
                .phone("222333444")
                .linkedIn("https://linkedin.com/in/abobr")
                .gitHub("https://github.com/abobr")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .candidateSkills(new ArrayList<>())
                .candidateExperiences(new ArrayList<>())
                .candidateProjects(new ArrayList<>())
                .candidateEducationStages(new ArrayList<>())
                .candidateCourses(new ArrayList<>())
                .hobby("Hobby")
                .userId(1)
                .build();
    }

    @Test
    void testGenerateCandidateCV() {
        // Given
        when(candidateService.findLoggedInCandidate()).thenReturn(candidate);
        when(candidateCVMapper.mapToDto(candidate)).thenReturn(expectedCvDetails);

        //When
        CandidateCVDTO result = candidateCVService.generateCandidateCV();

        //Then
        assertNotNull(result);
        assertEquals(candidate.getCandidateId(), result.getCandidateId());
        assertEquals(candidate.getName(), result.getName());
        assertEquals(candidate.getSurname(), result.getSurname());
        assertEquals(candidate.getEmail(), result.getEmail());
        assertEquals(candidate.getPhone(), result.getPhone());
        assertEquals(candidate.getLinkedIn(), result.getLinkedIn());
        assertEquals(candidate.getGitHub(), result.getGitHub());
        assertEquals(candidate.getTechSpecialization(), result.getTechSpecialization());
        assertEquals(candidate.getCandidateSkills(), result.getCandidateSkills());
        assertEquals(candidate.getCandidateExperiences(), result.getCandidateExperiences());
        assertEquals(candidate.getCandidateProjects(), result.getCandidateProjects());
        assertEquals(candidate.getCandidateEducationStages(), result.getCandidateEducationStages());
        assertEquals(candidate.getCandidateCourses(), result.getCandidateCourses());
        assertEquals(candidate.getProfilePhoto(), result.getProfilePhoto());
        assertEquals(candidate.getHobby(), result.getHobby());
        assertEquals(candidate.getUserId(), result.getUserId());

        verify(candidateService).findLoggedInCandidate();
        verify(candidateCVMapper).mapToDto(candidate);
    }

    @Test
    void testGenerateCandidateCV_WhenCandidateIsNull_ShouldThrowException() {
        // Given
        when(candidateService.findLoggedInCandidate()).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> candidateCVService.generateCandidateCV());
    }

    @Test
    void testGenerateCandidateCVPdf_WhenCandidateIsNull_ShouldThrowException() {
        // Given
        when(candidateService.findLoggedInCandidate()).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> candidateCVService.generateCandidateCVPdf(response));
    }
}
