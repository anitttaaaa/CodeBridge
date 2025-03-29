package pl.zajavka.CodeBridge.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.api.dto.mapper.EmployerMapper;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.business.dao.EmployerDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployerServiceTest {

    @Mock
    private EmployerDAO employerDAO;

    @Mock
    private CandidateDAO candidateDAO;

    @Mock
    private CandidateMapper candidateMapper;

    @Mock
    private EmployerMapper employerMapper;

    @InjectMocks
    private EmployerService employerService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    private Employer employer;

    @BeforeEach
    void setUp() {
        // Given
        employer = new Employer.EmployerBuilder()
                .email("test@example.com")
                .companyName("Test Company Name")
                .build();

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void shouldReturnEmployerWhenLoggedIn() {
        // Given
        when(employerDAO.findEmployerByEmail("test@example.com")).thenReturn(Optional.of(employer));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@example.com");

        // When
        Employer result = employerService.findLoggedInEmployer();

        // Then
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test Company Name", result.getCompanyName());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenEmployerNotFound() {
        // Given
        when(employerDAO.findEmployerByEmail("test@example.com")).thenReturn(Optional.empty());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@example.com");

        // When & Then
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            employerService.findLoggedInEmployer();
        });
        assertEquals("Could not find employer by email: [Optional.empty]", thrown.getMessage());
    }

    @Test
    void shouldReturnEmployerWhenEmployerExists() {
        // Given
        when(employerDAO.findEmployerByEmail("test@example.com")).thenReturn(Optional.of(employer));

        // When
        Employer result = employerService.findEmployerByEmail("test@example.com");

        // Then
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test Company Name", result.getCompanyName());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenEmployerNotFoundByEmail() {
        // Given
        when(employerDAO.findEmployerByEmail("test@example.com")).thenReturn(Optional.empty());

        // When & Then
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            employerService.findEmployerByEmail("test@example.com");
        });
        assertEquals("Could not find employer by email: [Optional.empty]", thrown.getMessage());
    }

    @Test
    void shouldCreateJobOffer() {
        // Given
        JobOffer jobOffer = new JobOffer.JobOfferBuilder()
                .jobOfferTitle("Software Engineer")
                .description("A job description.")
                .build();

        // When
        employerService.createJobOffer(jobOffer);

        // Then
        verify(employerDAO).createJobOffer(jobOffer);
    }

    @Test
    void shouldReturnFilteredCandidatesBasedOnTechSpecialization() {
        // Given
        Candidate candidate1 = Candidate.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        Candidate candidate2 = Candidate.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .status(StatusEnum.CURRENTLY_HIRED)
                .build();


        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        CandidateDTO candidateDTO2 = CandidateDTO.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .status(StatusEnum.CURRENTLY_HIRED)
                .build();

        when(candidateDAO.findAll()).thenReturn(Arrays.asList(candidate1, candidate2));
        when(candidateMapper.mapToDto(candidate1)).thenReturn(candidateDTO1);
        when(candidateMapper.mapToDto(candidate2)).thenReturn(candidateDTO2);

        // When
        List<CandidateDTO> result = employerService.getFilteredCandidates(TechSpecializationsEnum.BACKEND, null);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getCandidateId());
        assertEquals("Anna", result.get(0).getName());
    }
    @Test
    void shouldReturnFilteredCandidatesBasedOnStatus() {
        // Given
        Candidate candidate1 = Candidate.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        Candidate candidate2 = Candidate.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .status(StatusEnum.CURRENTLY_HIRED)
                .build();


        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        CandidateDTO candidateDTO2 = CandidateDTO.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .status(StatusEnum.CURRENTLY_HIRED)
                .build();

        when(candidateDAO.findAll()).thenReturn(Arrays.asList(candidate1, candidate2));
        when(candidateMapper.mapToDto(candidate1)).thenReturn(candidateDTO1);
        when(candidateMapper.mapToDto(candidate2)).thenReturn(candidateDTO2);

        // When
        List<CandidateDTO> result = employerService.getFilteredCandidates(null, StatusEnum.CURRENTLY_HIRED);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getCandidateId());
        assertEquals("Tomasz", result.get(0).getName());
    }

    @Test
    void shouldReturnEmptyListWhenNoCandidatesMatchFilters() {
        // Given
        Candidate candidate1 = Candidate.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        Candidate candidate2 = Candidate.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .status(StatusEnum.CURRENTLY_HIRED)
                .build();


        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        CandidateDTO candidateDTO2 = CandidateDTO.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .status(StatusEnum.CURRENTLY_HIRED)
                .build();

        when(candidateDAO.findAll()).thenReturn(Arrays.asList(candidate1, candidate2));
        when(candidateMapper.mapToDto(candidate1)).thenReturn(candidateDTO1);
        when(candidateMapper.mapToDto(candidate2)).thenReturn(candidateDTO2);

        // When
        List<CandidateDTO> result = employerService.getFilteredCandidates(TechSpecializationsEnum.BACKEND, StatusEnum.CURRENTLY_HIRED);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnAllCandidatesWhenNoFiltersApplied() {
        // Given
        Candidate candidate1 = Candidate.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        Candidate candidate2 = Candidate.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .status(StatusEnum.CURRENTLY_HIRED)
                .build();


        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        CandidateDTO candidateDTO2 = CandidateDTO.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .status(StatusEnum.CURRENTLY_HIRED)
                .build();

        when(candidateDAO.findAll()).thenReturn(Arrays.asList(candidate1, candidate2));
        when(candidateMapper.mapToDto(candidate1)).thenReturn(candidateDTO1);
        when(candidateMapper.mapToDto(candidate2)).thenReturn(candidateDTO2);

        // When
        List<CandidateDTO> result = employerService.getFilteredCandidates(null, null);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    void shouldReturnAllCandidatesSortedByIdDescending() {
        // Given
        Candidate candidate1 = Candidate.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .build();

        Candidate candidate2 = Candidate.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .build();

        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .build();

        CandidateDTO candidateDTO2 = CandidateDTO.builder()
                .candidateId(2)
                .name("Tomasz")
                .surname("Kowalski")
                .build();

        when(candidateDAO.findAllCandidates()).thenReturn(Arrays.asList(candidate1, candidate2));
        when(candidateMapper.mapToDto(candidate1)).thenReturn(candidateDTO1);
        when(candidateMapper.mapToDto(candidate2)).thenReturn(candidateDTO2);

        // When
        List<CandidateDTO> result = employerService.getAllCandidates();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getCandidateId());
        assertEquals(1, result.get(1).getCandidateId());

        verify(candidateMapper).mapToDto(candidate1);
        verify(candidateMapper).mapToDto(candidate2);
    }

    @Test
    void shouldReturnEmptyListWhenNoCandidatesFound() {
        // Given
        when(candidateDAO.findAllCandidates()).thenReturn(Collections.emptyList());

        // When
        List<CandidateDTO> result = employerService.getAllCandidates();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty(), "List should be empty");

        verify(candidateMapper, never()).mapToDto(any(Candidate.class));
    }


}
