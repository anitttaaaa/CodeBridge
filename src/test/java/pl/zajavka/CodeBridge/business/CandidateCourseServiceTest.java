package pl.zajavka.CodeBridge.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import pl.zajavka.CodeBridge.api.dto.CandidateCourseDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCourseMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateCourseDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateCourse;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateCourseServiceTest {

    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateCourseDAO candidateCourseDAO;

    @Mock
    private CandidateCourseMapper candidateCourseMapper;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CandidateCourseService candidateCourseService;

    private CandidateCourseDTO candidateCourseDTO;
    private Candidate candidate;
    private CandidateCourse candidateCourse;

    @BeforeEach
    void setUp() {
        candidateCourseDTO = new CandidateCourseDTO(
                1,
                "CodeBridge Academy",
                "Java Programming",
                "A comprehensive Java course for beginners",
                "Java, Spring, Hibernate",
                LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 9, 1)
        );

        candidate = new Candidate.Builder()
                .candidateId(1)
                .build();

        candidateCourse = new CandidateCourse(
                candidateCourseDTO.getCandidateCourseId(),
                candidateCourseDTO.getInstitution(),
                candidateCourseDTO.getCourseTitle(),
                candidateCourseDTO.getDescription(),
                candidateCourseDTO.getTechnologies(),
                candidateCourseDTO.getFromDate(),
                candidateCourseDTO.getToDate(),
                candidate.getCandidateId()
        );
    }


    @Test
    void shouldCreateCourseDataSuccessfully() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateCourseMapper.mapToDomain(candidateCourseDTO)).thenReturn(candidateCourse);
        when(candidateCourseDAO.createCourse(any(CandidateCourse.class), eq(1))).thenReturn(candidateCourse);

        // When
        candidateCourseService.createCourseData(candidateCourseDTO, authentication);

        // Then
        verify(candidateService, times(1)).findCandidateByEmail("test@example.com");
        verify(candidateCourseMapper, times(1)).mapToDomain(candidateCourseDTO);
        verify(candidateCourseDAO, times(1)).createCourse(any(CandidateCourse.class), eq(1));
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateCourseService.createCourseData(candidateCourseDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCreateCourseFails() {
        // Given

        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateCourseMapper.mapToDomain(candidateCourseDTO)).thenReturn(candidateCourse);
        when(candidateCourseDAO.createCourse(any(CandidateCourse.class), eq(1))).thenThrow(new RuntimeException("Database error")); // Symulujemy błąd w bazie danych

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            candidateCourseService.createCourseData(candidateCourseDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenMappingToDomainFails() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateCourseMapper.mapToDomain(candidateCourseDTO)).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateCourseService.createCourseData(candidateCourseDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCandidateCourseDTOIsNull() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(new Candidate.Builder().candidateId(1).build());

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateCourseService.createCourseData(null, authentication);
        });
    }

    @Test
    void shouldUpdateCandidateCourseSuccessfully() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateCourseMapper.mapToDomain(candidateCourseDTO)).thenReturn(candidateCourse);

        // When
        candidateCourseService.updateCandidateCourse(candidateCourseDTO, authentication);

        // Then
        verify(candidateService, times(1)).findCandidateByEmail("test@example.com");
        verify(candidateCourseMapper, times(1)).mapToDomain(candidateCourseDTO);
        verify(candidateCourseDAO, times(1)).updateCandidateCourse(candidateCourse, 1);
    }

    @Test
    void shouldThrowExceptionWhenCandidateCourseDTOIsNullOnUpdate() {

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateCourseService.updateCandidateCourse(null, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFoundOnUpdate() {
        // Given

        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateCourseService.updateCandidateCourse(candidateCourseDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenMappingToDomainFailsOnUpdate() {
        // Given
        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateCourseMapper.mapToDomain(candidateCourseDTO)).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            candidateCourseService.updateCandidateCourse(candidateCourseDTO, authentication);
        });
    }

    @Test
    void shouldThrowExceptionWhenUpdateFails() {
        // Given

        when(authentication.getName()).thenReturn("test@example.com");
        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateCourseMapper.mapToDomain(candidateCourseDTO)).thenReturn(candidateCourse);
        doThrow(new RuntimeException("Database error")).when(candidateCourseDAO).updateCandidateCourse(candidateCourse, 1);

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            candidateCourseService.updateCandidateCourse(candidateCourseDTO, authentication);
        });
    }

    @Test
    void shouldDeleteCandidateCourseById() {
        // Given
        Integer candidateCourseId = 1;

        // When
        candidateCourseService.deleteCandidateCourseById(candidateCourseId);

        // Then
        verify(candidateCourseDAO, times(1)).deleteById(candidateCourseId);
    }
}