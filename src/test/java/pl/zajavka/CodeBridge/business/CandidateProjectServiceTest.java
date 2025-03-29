package pl.zajavka.CodeBridge.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import pl.zajavka.CodeBridge.api.dto.CandidateProjectDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateProjectMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateProjectDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateProject;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateProjectServiceTest {

    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateProjectDAO candidateProjectDAO;

    @Mock
    private CandidateProjectMapper candidateProjectMapper;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CandidateProjectService candidateProjectService;

    private CandidateProjectDTO candidateProjectDTO;
    private Candidate candidate;
    private CandidateProject candidateProject;

    @BeforeEach
    void setUp() {
        candidateProjectDTO = new CandidateProjectDTO(
                1,
                "CodeBridge Project",
                "Developed a web application for online learning",
                "Java, Spring Boot, React",
                LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 9, 1),
                "http://example.com/project"
        );

        candidate = new Candidate.Builder()
                .candidateId(1)
                .build();

        candidateProject = new CandidateProject(
                candidateProjectDTO.getCandidateProjectId(),
                candidateProjectDTO.getProjectTitle(),
                candidateProjectDTO.getDescription(),
                candidateProjectDTO.getTechnologies(),
                candidateProjectDTO.getFromDate(),
                candidateProjectDTO.getToDate(),
                candidateProjectDTO.getProjectLink(),
                candidate.getCandidateId()
        );
    }

    @Test
    void shouldCreateProjectDataSuccessfully() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateProjectMapper.mapToDomain(candidateProjectDTO)).thenReturn(candidateProject);
        when(candidateProjectDAO.createProject(any(CandidateProject.class), eq(1))).thenReturn(candidateProject);

        // When
        candidateProjectService.createProjectData(candidateProjectDTO, authentication);

        // Then
        verify(candidateService, times(1)).findCandidateByEmail("test@example.com");
        verify(candidateProjectMapper, times(1)).mapToDomain(candidateProjectDTO);
        verify(candidateProjectDAO, times(1)).createProject(any(CandidateProject.class), eq(1));
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateProjectService.createProjectData(candidateProjectDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCreateProjectFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateProjectMapper.mapToDomain(candidateProjectDTO)).thenReturn(candidateProject);
        when(candidateProjectDAO.createProject(any(CandidateProject.class), eq(1))).thenThrow(new RuntimeException("Database error"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            candidateProjectService.createProjectData(candidateProjectDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenMappingToDomainFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateProjectMapper.mapToDomain(candidateProjectDTO)).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateProjectService.createProjectData(candidateProjectDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCandidateProjectDTOIsNull() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(new Candidate.Builder().candidateId(1).build());

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateProjectService.createProjectData(null, authentication);
        });
    }

    @Test
    void shouldUpdateCandidateProjectSuccessfully() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateProjectMapper.mapToDomain(candidateProjectDTO)).thenReturn(candidateProject);

        // When
        candidateProjectService.updateCandidateProject(candidateProjectDTO, authentication);

        // Then
        verify(candidateService, times(1)).findCandidateByEmail("test@example.com");
        verify(candidateProjectMapper, times(1)).mapToDomain(candidateProjectDTO);
        verify(candidateProjectDAO, times(1)).updateCandidateProject(candidateProject, 1);
    }

    @Test
    void shouldThrowExceptionWhenCandidateProjectDTOIsNullOnUpdate() {
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateProjectService.updateCandidateProject(null, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFoundOnUpdate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateProjectService.updateCandidateProject(candidateProjectDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenMappingToDomainFailsOnUpdate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateProjectMapper.mapToDomain(candidateProjectDTO)).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateProjectService.updateCandidateProject(candidateProjectDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenUpdateFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateProjectMapper.mapToDomain(candidateProjectDTO)).thenReturn(candidateProject);
        doThrow(new RuntimeException("Database error")).when(candidateProjectDAO).updateCandidateProject(candidateProject, 1);

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            candidateProjectService.updateCandidateProject(candidateProjectDTO, authentication);
        });
    }

    @Test
    void shouldDeleteCandidateProjectById() {
        // Given
        Integer candidateProjectId = 1;

        // When
        candidateProjectService.deleteCandidateProjectById(candidateProjectId);

        // Then
        verify(candidateProjectDAO, times(1)).deleteById(candidateProjectId);
    }
}
