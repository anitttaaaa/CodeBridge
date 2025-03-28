package pl.zajavka.CodeBridge.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import pl.zajavka.CodeBridge.api.dto.CandidateEducationDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateEducationMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateEducationDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateEducationServiceTest {

    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateEducationDAO candidateEducationDAO;

    @Mock
    private CandidateEducationMapper candidateEducationMapper;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CandidateEducationService candidateEducationService;

    private CandidateEducationDTO candidateEducationDTO;
    private Candidate candidate;
    private CandidateEducation candidateEducation;

    @BeforeEach
    void setUp() {
        candidateEducationDTO = new CandidateEducationDTO(
                1,
                "CodeBridge Academy",
                "Computer Science",
                "Bachelor's degree in Computer Science",
                LocalDate.of(2020, 9, 1),
                LocalDate.of(2024, 6, 1)
        );

        candidate = new Candidate.Builder().candidateId(1).build();

        candidateEducation = new CandidateEducation.Builder()
                .candidateEducationId(candidateEducationDTO.getCandidateEducationId())
                .institution(candidateEducationDTO.getInstitution())
                .degree(candidateEducationDTO.getDegree())
                .fieldOfStudy(candidateEducationDTO.getFieldOfStudy())
                .fromDate(candidateEducationDTO.getFromDate())
                .toDate(candidateEducationDTO.getToDate())
                .candidateId(candidate.getCandidateId())
                .build();
    }

    @Test
    void shouldCreateEducationDataSuccessfully() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateEducationMapper.mapToDomain(candidateEducationDTO)).thenReturn(candidateEducation);
        when(candidateEducationDAO.createEducation(any(CandidateEducation.class), eq(1))).thenReturn(candidateEducation);

        // When
        candidateEducationService.createEducationData(candidateEducationDTO, authentication);

        // Then
        verify(candidateService, times(1)).findCandidateByEmail("test@example.com");
        verify(candidateEducationMapper, times(1)).mapToDomain(candidateEducationDTO);
        verify(candidateEducationDAO, times(1)).createEducation(any(CandidateEducation.class), eq(1));
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFoundForCreate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateEducationService.createEducationData(candidateEducationDTO, authentication);
        });
    }
    @Test
    void shouldThrowExceptionWhenCandidateEducationDTOIsNullForCreate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateEducationService.createEducationData(null, authentication);
        });
    }
    @Test
    void shouldThrowExceptionWhenCreateFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateEducationMapper.mapToDomain(candidateEducationDTO)).thenReturn(candidateEducation);
        when(candidateEducationDAO.createEducation(any(CandidateEducation.class), eq(1))).thenThrow(new RuntimeException("Database error"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            candidateEducationService.createEducationData(candidateEducationDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenMappingToDomainFailsForCreate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateEducationMapper.mapToDomain(candidateEducationDTO)).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateEducationService.createEducationData(candidateEducationDTO, authentication);
        });
    }


    @Test
    void shouldUpdateEducationDataSuccessfully() throws AccessDeniedException {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateEducationMapper.mapToDomain(candidateEducationDTO)).thenReturn(candidateEducation);

        // When
        candidateEducationService.updateCandidateEducation(candidateEducationDTO, authentication);

        // Then
        verify(candidateService, times(1)).findCandidateByEmail("test@example.com");
        verify(candidateEducationMapper, times(1)).mapToDomain(candidateEducationDTO);
        verify(candidateEducationDAO, times(1)).updateCandidateEducation(candidateEducation, 1);
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFoundForUpdate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateEducationService.updateCandidateEducation(candidateEducationDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCandidateEducationDTOIsNullForUpdate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateEducationService.updateCandidateEducation(null, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenUpdateFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateEducationMapper.mapToDomain(candidateEducationDTO)).thenReturn(candidateEducation);
        doThrow(new RuntimeException("Database error")).when(candidateEducationDAO).updateCandidateEducation(candidateEducation, 1);

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            candidateEducationService.updateCandidateEducation(candidateEducationDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenMappingFailsOnUpdate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateEducationMapper.mapToDomain(candidateEducationDTO)).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateEducationService.updateCandidateEducation(candidateEducationDTO, authentication);
        });
    }

    @Test
    void shouldDeleteCandidateEducationById() {
        // Given
        Integer candidateEducationId = 1;

        // When
        candidateEducationService.deleteCandidateEducationById(candidateEducationId);

        // Then
        verify(candidateEducationDAO, times(1)).deleteById(candidateEducationId);
    }
}
