package pl.zajavka.CodeBridge.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateExperienceMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateExperienceDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateExperienceServiceTest {

    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateExperienceDAO candidateExperienceDAO;

    @Mock
    private CandidateExperienceMapper candidateExperienceMapper;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CandidateExperienceService candidateExperienceService;

    private CandidateExperienceDTO candidateExperienceDTO;
    private Candidate candidate;
    private CandidateExperience candidateExperience;

    @BeforeEach
    void setUp() {
        candidateExperienceDTO = new CandidateExperienceDTO(
                1,
                "Company XYZ",
                "Software Engineer",
                "Developed enterprise-level applications",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2023, 1, 1)

        );

        candidate = new Candidate.Builder().
                candidateId(1)
                .build();

        candidateExperience = new CandidateExperience(
                candidateExperienceDTO.getCandidateExperienceId(),
                candidateExperienceDTO.getCompanyName(),
                candidateExperienceDTO.getCandidatePosition(),
                candidateExperienceDTO.getDescription(),
                candidateExperienceDTO.getFromDate(),
                candidateExperienceDTO.getToDate(),
                candidate.getCandidateId()
        );
    }

    @Test
    void shouldCreateExperienceDataSuccessfully() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateExperienceMapper.mapToDomain(candidateExperienceDTO)).thenReturn(candidateExperience);
        when(candidateExperienceDAO.createExperience(any(CandidateExperience.class), eq(1))).thenReturn(candidateExperience);

        // When
        candidateExperienceService.createExperienceData(candidateExperienceDTO, authentication);

        // Then
        verify(candidateService, times(1)).findCandidateByEmail("test@example.com");
        verify(candidateExperienceMapper, times(1)).mapToDomain(candidateExperienceDTO);
        verify(candidateExperienceDAO, times(1)).createExperience(any(CandidateExperience.class), eq(1));
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateExperienceService.createExperienceData(candidateExperienceDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCreateExperienceFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateExperienceMapper.mapToDomain(candidateExperienceDTO)).thenReturn(candidateExperience);
        when(candidateExperienceDAO.createExperience(any(CandidateExperience.class), eq(1))).thenThrow(new RuntimeException("Database error"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            candidateExperienceService.createExperienceData(candidateExperienceDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenMappingToDomainFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateExperienceMapper.mapToDomain(candidateExperienceDTO)).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateExperienceService.createExperienceData(candidateExperienceDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCandidateExperienceDTOIsNull() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(new Candidate.Builder().candidateId(1).build());

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateExperienceService.createExperienceData(null, authentication);
        });
    }

    @Test
    void shouldUpdateCandidateExperienceSuccessfully() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateExperienceMapper.mapToDomain(candidateExperienceDTO)).thenReturn(candidateExperience);

        // When
        candidateExperienceService.updateCandidateExperience(candidateExperienceDTO, authentication);

        // Then
        verify(candidateService, times(1)).findCandidateByEmail("test@example.com");
        verify(candidateExperienceMapper, times(1)).mapToDomain(candidateExperienceDTO);
        verify(candidateExperienceDAO, times(1)).updateCandidateExperience(candidateExperience, 1);
    }

    @Test
    void shouldThrowExceptionWhenCandidateExperienceDTOIsNullOnUpdate() {
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateExperienceService.updateCandidateExperience(null, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFoundOnUpdate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateExperienceService.updateCandidateExperience(candidateExperienceDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenMappingToDomainFailsOnUpdate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateExperienceMapper.mapToDomain(candidateExperienceDTO)).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateExperienceService.updateCandidateExperience(candidateExperienceDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenUpdateFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateExperienceMapper.mapToDomain(candidateExperienceDTO)).thenReturn(candidateExperience);
        doThrow(new RuntimeException("Database error")).when(candidateExperienceDAO).updateCandidateExperience(candidateExperience, 1);

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            candidateExperienceService.updateCandidateExperience(candidateExperienceDTO, authentication);
        });
    }

    @Test
    void shouldDeleteCandidateExperienceById() {
        // Given
        Integer candidateExperienceId = 1;

        // When
        candidateExperienceService.deleteCandidateExperienceById(candidateExperienceId);

        // Then
        verify(candidateExperienceDAO, times(1)).deleteById(candidateExperienceId);
    }
}
