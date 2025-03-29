package pl.zajavka.CodeBridge.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.api.enums.SkillsEnum;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @Mock
    private CandidateDAO candidateDAO;

    @Mock
    private CandidateMapper candidateMapper;

    @InjectMocks
    private CandidateService candidateService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void shouldReturnLoggedInCandidate() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .build();

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        Candidate result = candidateService.findLoggedInCandidate();

        // Then
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(candidateDAO, times(1)).findCandidateByEmail(email);
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Given
        String email = "nonexistent@example.com";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> candidateService.findLoggedInCandidate());
    }

    @Test
    void shouldReturnCandidateByEmail() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .build();

        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        Candidate result = candidateService.findCandidateByEmail(email);

        // Then
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(candidateDAO, times(1)).findCandidateByEmail(email);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCandidateByEmailNotFound() {
        // Given
        String email = "unknown@example.com";

        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> candidateService.findCandidateByEmail(email));
    }

    @Test
    void shouldUpdateCandidateSkills() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .candidateId(1)
                .userId(1)
                .email(email)
                .candidateSkills(List.of(SkillsEnum.JAVA))
                .build();

        List<SkillsEnum> newSkills = List.of(SkillsEnum.PYTHON, SkillsEnum.JAVA);

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateSkills(authentication, newSkills);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getCandidateId().equals(mockCandidate.getCandidateId()) &&
                        updatedCandidate.getUserId().equals(mockCandidate.getUserId()) &&
                        updatedCandidate.getCandidateSkills().containsAll(newSkills)
        ));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCandidateNotFound() {
        // Given
        String email = "test@example.com";
        List<SkillsEnum> newSkills = List.of(SkillsEnum.PYTHON);

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> candidateService.updateCandidateSkills(authentication, newSkills));
        verify(candidateDAO, never()).updateCandidate(any());
    }

    @Test
    void shouldUpdateCandidateWithEmptySkillsList() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .candidateId(1)
                .userId(1)
                .email(email)
                .candidateSkills(List.of(SkillsEnum.JAVA))
                .build();

        List<SkillsEnum> emptySkills = List.of();

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateSkills(authentication, emptySkills);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getCandidateId().equals(mockCandidate.getCandidateId()) &&
                        updatedCandidate.getUserId().equals(mockCandidate.getUserId()) &&
                        updatedCandidate.getCandidateSkills().isEmpty()
        ));
    }

    @Test
    void shouldUpdateCandidateTechSpecialization() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .build();

        TechSpecializationsEnum newSpecialization = TechSpecializationsEnum.BACKEND;

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateTechSpecialization(authentication, newSpecialization);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getTechSpecialization().equals(newSpecialization)
        ));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingTechSpecializationForNonexistentCandidate() {
        // Given
        String email = "test@example.com";
        TechSpecializationsEnum newSpecialization = TechSpecializationsEnum.BACKEND;

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> candidateService.updateCandidateTechSpecialization(authentication, newSpecialization));
    }

    @Test
    void shouldUpdateCandidateTechSpecializationToNull() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .techSpecialization(TechSpecializationsEnum.FRONTEND)
                .build();

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateTechSpecialization(authentication, null);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getTechSpecialization() == null
        ));
    }

    @Test
    void shouldUpdateCandidateStatus() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(100)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        StatusEnum newStatus = StatusEnum.CURRENTLY_HIRED;

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateStatus(authentication, newStatus);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getStatus().equals(newStatus)
        ));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingStatusForNonexistentCandidate() {
        // Given
        String email = "test@example.com";
        StatusEnum newStatus = StatusEnum.CURRENTLY_HIRED;

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> candidateService.updateCandidateStatus(authentication, newStatus));
    }

    @Test
    void shouldUpdateCandidateStatusToNull() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(100)
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateStatus(authentication, null);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getStatus() == null
        ));
    }

    @Test
    void shouldUpdateCandidateBasicInfo() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(100)
                .name("OldName")
                .surname("OldSurname")
                .phone("123456789")
                .linkedIn("old_linkedin")
                .gitHub("old_github")
                .build();

        String newName = "NewName";
        String newSurname = "NewSurname";
        String newPhone = "987654321";
        String newLinkedIn = "new_linkedin";
        String newGitHub = "new_github";

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateBasicInfo(authentication, newName, newSurname, newPhone, newLinkedIn, newGitHub);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getName().equals(newName) &&
                        updatedCandidate.getSurname().equals(newSurname) &&
                        updatedCandidate.getPhone().equals(newPhone) &&
                        updatedCandidate.getLinkedIn().equals(newLinkedIn) &&
                        updatedCandidate.getGitHub().equals(newGitHub)
        ));
    }
    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingBasicInfoForNonexistentCandidate() {
        // Given
        String email = "test@example.com";

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> candidateService.updateCandidateBasicInfo(authentication, "NewName", "NewSurname", "987654321", "new_linkedin", "new_github"));
    }
    @Test
    void shouldUpdateCandidateHobby() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(100)
                .hobby("Old Hobby")
                .build();

        String newHobby = "New Hobby";

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateHobby(authentication, newHobby);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getHobby().equals(newHobby)
        ));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingHobbyForNonexistentCandidate() {
        // Given
        String email = "test@example.com";

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> candidateService.updateCandidateHobby(authentication, "New Hobby"));
    }

    @Test
    void shouldSetHobbyToNullWhenGivenEmptyString() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(100)
                .hobby("Old Hobby")
                .build();

        String emptyHobby = " ";

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateHobby(authentication, emptyHobby);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getHobby() == null
        ));

    }
    @Test
    void shouldUpdateCandidateAboutMe() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(100)
                .aboutMe("Old About Me")
                .build();

        String newAboutMe = "New About Me";

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateAboutMe(authentication, newAboutMe);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getAboutMe().equals(newAboutMe)
        ));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingAboutMeForNonexistentCandidate() {
        // Given
        String email = "test@example.com";

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> candidateService.updateCandidateAboutMe(authentication, "New About Me"));
    }

    @Test
    void shouldSetAboutMeToNullWhenGivenEmptyString() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(100)
                .aboutMe("Old About Me")
                .build();

        String emptyAboutMe = " ";

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.updateCandidateAboutMe(authentication, emptyAboutMe);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getAboutMe() == null
        ));
    }
    @Test
    void shouldReturnCandidateDetailsForEmployer() {
        // Given
        String email = "test@example.com";
        Candidate mockCandidate = Candidate.builder()
                .candidateId(1)
                .email(email)
                .name("Anna")
                .surname("Nowak")
                .build();

        CandidateDTO mockCandidateDTO = CandidateDTO.builder()
                .candidateId(1)
                .email(email)
                .name("Anna")
                .surname("Nowak")
                .candidateExperiences(List.of())
                .candidateProjects(List.of())
                .candidateEducationStages(List.of())
                .candidateCourses(List.of())
                .build();

        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));
        when(candidateMapper.mapToDto(mockCandidate)).thenReturn(mockCandidateDTO);

        // When
        CandidateDTO result = candidateService.getCandidateDetailsByEmployer(email);

        // Then
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals("Anna", result.getName());
        assertEquals("Nowak", result.getSurname());
        verify(candidateDAO, times(1)).findCandidateByEmail(email);
        verify(candidateMapper, times(1)).mapToDto(mockCandidate);
    }
    @Test
    void shouldThrowNotFoundExceptionWhenCandidateNotFoundForEmployer() {
        // Given
        String email = "test@example.com";
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> candidateService.getCandidateDetailsByEmployer(email));
    }
    @Test
    void shouldReturnSortedCandidateDetails() {
        // Given
        Candidate mockCandidate = Candidate.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .email("test@example.com")
                .phone("123456789")
                .status(StatusEnum.LOOKING_FOR_A_JOB)
                .build();

        CandidateExperienceDTO experience1 = CandidateExperienceDTO.builder()
                .fromDate(LocalDate.of(2024, 1, 1))
                .build();
        CandidateExperienceDTO experience2 = CandidateExperienceDTO.builder()
                .fromDate(LocalDate.of(2023, 1, 1))
                .build();

        CandidateProjectDTO project1 = CandidateProjectDTO.builder()
                .fromDate(LocalDate.of(2022, 1, 1))
                .build();
        CandidateProjectDTO project2 = CandidateProjectDTO.builder()
                .fromDate(LocalDate.of(2021, 1, 1))
                .build();

        CandidateEducationDTO education1 = CandidateEducationDTO.builder()
                .fromDate(LocalDate.of(2020, 1, 1))
                .build();
        CandidateEducationDTO education2 = CandidateEducationDTO.builder()
                .fromDate(LocalDate.of(2019, 1, 1))
                .build();

        CandidateCourseDTO course1 = CandidateCourseDTO.builder()
                .fromDate(LocalDate.of(2023, 1, 1))
                .build();
        CandidateCourseDTO course2 = CandidateCourseDTO.builder()
                .fromDate(LocalDate.of(2022, 1, 1))
                .build();

        CandidateDTO mockCandidateDTO = CandidateDTO.builder()
                .candidateId(1)
                .name("Anna")
                .surname("Nowak")
                .email("test@example.com")
                .candidateExperiences(List.of(experience1, experience2))
                .candidateProjects(List.of(project1, project2))
                .candidateEducationStages(List.of(education1, education2))
                .candidateCourses(List.of(course1, course2))
                .build();

        when(candidateMapper.mapToDto(mockCandidate)).thenReturn(mockCandidateDTO);

        // When
        CandidateDTO result = candidateService.getSortedCandidateDetails(mockCandidate);

        // Then
        assertNotNull(result);

        assertEquals(2, result.getCandidateExperiences().size());
        assertEquals(LocalDate.of(2023, 1, 1), result.getCandidateExperiences().get(0).getFromDate());
        assertEquals(LocalDate.of(2024, 1, 1), result.getCandidateExperiences().get(1).getFromDate());

        assertEquals(2, result.getCandidateProjects().size());
        assertEquals(LocalDate.of(2021, 1, 1), result.getCandidateProjects().get(0).getFromDate());
        assertEquals(LocalDate.of(2022, 1, 1), result.getCandidateProjects().get(1).getFromDate());

        assertEquals(2, result.getCandidateEducationStages().size());
        assertEquals(LocalDate.of(2019, 1, 1), result.getCandidateEducationStages().get(0).getFromDate());
        assertEquals(LocalDate.of(2020, 1, 1), result.getCandidateEducationStages().get(1).getFromDate());

        assertEquals(2, result.getCandidateCourses().size());
        assertEquals(LocalDate.of(2022, 1, 1), result.getCandidateCourses().get(0).getFromDate());
        assertEquals(LocalDate.of(2023, 1, 1), result.getCandidateCourses().get(1).getFromDate());

        verify(candidateMapper, times(1)).mapToDto(mockCandidate);
    }

    @Test
    void shouldReturnProfilePhoto() {
        // Given
        String email = "test@example.com";
        byte[] mockProfilePhoto = new byte[]{1, 2, 3};
        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .build();

        CandidateDTO mockCandidateDTO = new CandidateDTO.Builder()
                .profilePhoto(mockProfilePhoto)
                .build();

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));
        when(candidateMapper.mapToDto(mockCandidate)).thenReturn(mockCandidateDTO);

        // When
        byte[] result = candidateService.getProfilePhoto(authentication);

        // Then
        assertNotNull(result);
        assertEquals(mockProfilePhoto, result);
        verify(candidateDAO, times(1)).findCandidateByEmail(email);
        verify(candidateMapper, times(1)).mapToDto(mockCandidate);
    }

    @Test
    void shouldUpdateCandidateProfilePhoto() throws IOException {
        // Given
        String email = "test@example.com";
        byte[] newProfilePhoto = new byte[]{4, 5, 6};
        MultipartFile mockProfilePhoto = mock(MultipartFile.class);

        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(1)
                .name("John")
                .surname("Doe")
                .profilePhoto(new byte[]{1, 2, 3})
                .build();


        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));
        when(mockProfilePhoto.isEmpty()).thenReturn(false);
        when(mockProfilePhoto.getBytes()).thenReturn(newProfilePhoto);

        // When
        candidateService.updateCandidateProfilePhoto(authentication, mockProfilePhoto);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getProfilePhoto() != null &&
                        updatedCandidate.getProfilePhoto().length == newProfilePhoto.length &&
                        updatedCandidate.getProfilePhoto()[0] == newProfilePhoto[0]
        ));
    }

    @Test
    void shouldDeleteCandidateProfilePhoto() {
        // Given
        String email = "test@example.com";
        byte[] profilePhoto = new byte[]{1, 2, 3}; 

        Candidate mockCandidate = new Candidate.Builder()
                .email(email)
                .candidateId(1)
                .userId(1)
                .name("John")
                .surname("Doe")
                .profilePhoto(profilePhoto)
                .build();

        when(authentication.getName()).thenReturn(email);
        when(candidateDAO.findCandidateByEmail(email)).thenReturn(Optional.of(mockCandidate));

        // When
        candidateService.deleteCandidateProfilePhoto(authentication);

        // Then
        verify(candidateDAO, times(1)).updateCandidate(argThat(updatedCandidate ->
                updatedCandidate.getProfilePhoto() == null
        ));
    }


}
