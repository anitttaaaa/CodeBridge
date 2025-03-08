package pl.zajavka.CodeBridge.api.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.dto.mapper.*;
import pl.zajavka.CodeBridge.business.*;
import pl.zajavka.CodeBridge.domain.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Controller
public class CandidatePortalController {

    private static final String SHOW_CANDIDATE_PORTAL = "/candidate-portal";
    private static final String PROFILE_PHOTO_DISPLAY = "/candidate-portal/profilePhoto/{email}";

    private static final String ADD_CANDIDATE_EXPERIENCE = "/candidate-portal/add-candidate-experience";
    private static final String ADD_CANDIDATE_PROJECT = "/candidate-portal/add-candidate-project";
    private static final String ADD_CANDIDATE_EDUCATION = "/candidate-portal/add-candidate-education";
    private static final String ADD_CANDIDATE_COURSE = "/candidate-portal/add-candidate-course";
    private static final String UPDATE_CANDIDATE_BASIC_INFO = "/candidate-portal/update-candidate-basic-info";
    private static final String UPDATE_CANDIDATE_TECH_SPECIALIZATION = "/candidate-portal/update-candidate-tech-specialization";
    private static final String UPDATE_CANDIDATE_STATUS = "/candidate-portal/update-candidate-status";
    private static final String UPDATE_CANDIDATE_SKILLS = "/candidate-portal/update-candidate-skills";
    private static final String UPDATE_CANDIDATE_EXPERIENCE = "/candidate-portal/update-candidate-experience";
    private static final String UPDATE_CANDIDATE_PROJECT = "/candidate-portal/update-candidate-project";
    private static final String UPDATE_CANDIDATE_EDUCATION = "/candidate-portal/update-candidate-education";
    private static final String UPDATE_CANDIDATE_COURSE = "/candidate-portal/update-candidate-course";
    private static final String UPDATE_CANDIDATE_ABOUT_ME = "/candidate-portal/update-candidate-about-me";
    private static final String UPDATE_CANDIDATE_HOBBY = "/candidate-portal/update-candidate-hobby";
    private static final String UPDATE_CANDIDATE_PHOTO = "/candidate-portal/update-candidate-photo";

    private static final String DELETE_CANDIDATE_PHOTO = "/candidate-portal/delete-candidate-photo/{email}";
    private static final String DELETE_CANDIDATE_EXPERIENCE = "/candidate-portal/delete-experience";
    private static final String DELETE_CANDIDATE_PROJECT = "/candidate-portal/delete-project";
    private static final String DELETE_CANDIDATE_EDUCATION = "/candidate-portal/delete-education";
    private static final String DELETE_CANDIDATE_COURSE = "/candidate-portal/delete-course";


    private final CandidateService candidateService;
    private final CandidateExperienceService candidateExperienceService;
    private final CandidateProjectService candidateProjectService;
    private final CandidateCourseService candidateCourseService;
    private final CandidateEducationService candidateEducationService;
    private final CandidateExperienceMapper candidateExperienceMapper;
    private final CandidateProjectMapper candidateProjectMapper;
    private final CandidateCourseMapper candidateCourseMapper;
    private final CandidateEducationMapper candidateEducationMapper;
    private final CandidateMapper candidateMapper;

    public CandidatePortalController(CandidateService candidateService,
                                     CandidateExperienceService candidateExperienceService,
                                     CandidateProjectService candidateProjectService,
                                     CandidateCourseService candidateCourseService,
                                     CandidateEducationService candidateEducationService,
                                     CandidateExperienceMapper candidateExperienceMapper,
                                     CandidateProjectMapper candidateProjectMapper,
                                     CandidateCourseMapper candidateCourseMapper,
                                     CandidateEducationMapper candidateEducationMapper,
                                     CandidateMapper candidateMapper) {
        this.candidateService = candidateService;
        this.candidateExperienceService = candidateExperienceService;
        this.candidateProjectService = candidateProjectService;
        this.candidateCourseService = candidateCourseService;
        this.candidateEducationService = candidateEducationService;
        this.candidateExperienceMapper = candidateExperienceMapper;
        this.candidateProjectMapper = candidateProjectMapper;
        this.candidateCourseMapper = candidateCourseMapper;
        this.candidateEducationMapper = candidateEducationMapper;
        this.candidateMapper = candidateMapper;
    }

    @GetMapping(SHOW_CANDIDATE_PORTAL)
    public String getCandidateDetails(Model model) {

        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateDTO candidateDetails = candidateMapper.mapToDto(candidate);

        List<CandidateExperienceDTO> sortedExperiences = candidateDetails.getCandidateExperiences()
                .stream()
                .sorted(Comparator.comparing(CandidateExperienceDTO::getFromDate))
                .toList();

        List<CandidateProjectDTO> sortedProjects = candidateDetails.getCandidateProjects()
                .stream()
                .sorted(Comparator.comparing(CandidateProjectDTO::getFromDate))
                .toList();

        List<CandidateEducationDTO> sortedCandidateEducationStages = candidateDetails.getCandidateEducationStages()
                .stream()
                .sorted(Comparator.comparing(CandidateEducationDTO::getFromDate))
                .toList();

        List<CandidateCourseDTO> sortedCourses = candidateDetails.getCandidateCourses()
                .stream()
                .sorted(Comparator.comparing(CandidateCourseDTO::getFromDate))
                .toList();

        model.addAttribute("candidateExperiences", sortedExperiences);
        model.addAttribute("candidateProjects", sortedProjects);
        model.addAttribute("candidateEducationStages", sortedCandidateEducationStages);
        model.addAttribute("candidateCourses", sortedCourses);
        model.addAttribute("candidate", candidateDetails);

        System.out.println(sortedExperiences);
        return "candidate_portal";
    }


    @PostMapping(UPDATE_CANDIDATE_TECH_SPECIALIZATION)
    public String updateCandidateTechSpecialization(
            @RequestParam(value = "techSpecialization", required = false) String techSpecialization,
            Authentication authentication
    ) {
        // Znalezienie kandydata po e-mailu
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Jeżeli specjalizacja technologiczna jest pusta lub null, ustawiamy ją na null
        if (techSpecialization == null || techSpecialization.trim().isEmpty()) {
            techSpecialization = null;
        }

        // Tworzenie nowego kandydata z użyciem buildera
        candidate = new Candidate.Builder()
                .phone(candidate.getPhone())
                .status(candidate.getStatus())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(techSpecialization)  // Nowa specjalizacja
                .aboutMe(candidate.getAboutMe())
                .hobby(candidate.getHobby())
                .userId(candidate.getUserId())
                .profilePhoto(candidate.getProfilePhoto())
                .candidateSkills(candidate.getCandidateSkills())
                .candidateExperiences(candidate.getCandidateExperiences())
                .candidateProjects(candidate.getCandidateProjects())
                .candidateEducationStages(candidate.getCandidateEducationStages())
                .candidateCourses(candidate.getCandidateCourses())
                .build();

        // Aktualizacja kandydata w bazie danych
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }



    @PostMapping(UPDATE_CANDIDATE_STATUS)
    public String updateCandidateStatus(
            @RequestParam(value = "status", required = false) String status,
            Authentication authentication
    ) {
        // Znalezienie kandydata po e-mailu
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Jeżeli status jest pusty lub null, ustawiamy go na null
        if (status == null || status.trim().isEmpty()) {
            status = null;
        }

        // Tworzenie nowego kandydata z użyciem buildera
        candidate = new Candidate.Builder()
                .phone(candidate.getPhone())
                .status(status)  // Nowy status
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .aboutMe(candidate.getAboutMe())
                .hobby(candidate.getHobby())
                .userId(candidate.getUserId())
                .profilePhoto(candidate.getProfilePhoto())
                .candidateSkills(candidate.getCandidateSkills())
                .candidateExperiences(candidate.getCandidateExperiences())
                .candidateProjects(candidate.getCandidateProjects())
                .candidateEducationStages(candidate.getCandidateEducationStages())
                .candidateCourses(candidate.getCandidateCourses())
                .build();

        // Aktualizacja kandydata w bazie danych
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }



    @GetMapping(PROFILE_PHOTO_DISPLAY)
    public ResponseEntity<byte[]> getProfilePhoto(
            Authentication authentication) {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        CandidateDTO candidateDTO = candidateMapper.mapToDto(candidate);
        byte[] profilePhoto = candidateDTO.getProfilePhoto();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(profilePhoto);

    }

    @PostMapping(UPDATE_CANDIDATE_PHOTO)
    public String updateCandidateProfilePhoto(
            @RequestParam("profilePhoto") MultipartFile profilePhoto,
            Authentication authentication
    ) throws IOException {
        // Znalezienie kandydata po e-mailu
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzanie, czy plik nie jest pusty
        if (!profilePhoto.isEmpty()) {
            byte[] profilePhotoData = profilePhoto.getBytes();

            // Tworzenie nowego kandydata z użyciem buildera
            candidate = new Candidate.Builder()
                    .phone(candidate.getPhone())
                    .status(candidate.getStatus())
                    .linkedIn(candidate.getLinkedIn())
                    .gitHub(candidate.getGitHub())
                    .techSpecialization(candidate.getTechSpecialization())
                    .aboutMe(candidate.getAboutMe())
                    .hobby(candidate.getHobby())
                    .userId(candidate.getUserId())
                    .profilePhoto(profilePhotoData)  // Nowe zdjęcie profilowe
                    .candidateSkills(candidate.getCandidateSkills())
                    .candidateExperiences(candidate.getCandidateExperiences())
                    .candidateProjects(candidate.getCandidateProjects())
                    .candidateEducationStages(candidate.getCandidateEducationStages())
                    .candidateCourses(candidate.getCandidateCourses())
                    .build();

            // Aktualizacja kandydata w bazie danych
            candidateService.updateCandidate(candidate, authentication);
        }

        return "redirect:/candidate-portal";
    }



    @PostMapping(UPDATE_CANDIDATE_BASIC_INFO)
    public String updateCandidateAllDetails(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("phone") String phone,
            @RequestParam(value = "linkedIn", required = false) String linkedIn,
            @RequestParam(value = "gitHub", required = false) String gitHub,
            Authentication authentication
    ) {
        // Znalezienie kandydata po e-mailu
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Tworzenie nowego kandydata z użyciem buildera
        candidate = new Candidate.Builder()
                .phone(phone)  // Nowy numer telefonu
                .status(candidate.getStatus())  // Zachowanie dotychczasowego statusu
                .linkedIn(linkedIn)  // Nowy LinkedIn
                .gitHub(gitHub)  // Nowy GitHub
                .techSpecialization(candidate.getTechSpecialization())  // Zachowanie dotychczasowej specjalizacji technologicznej
                .aboutMe(candidate.getAboutMe())  // Zachowanie dotychczasowych danych o kandydacie
                .hobby(candidate.getHobby())  // Zachowanie dotychczasowych hobby
                .userId(candidate.getUserId())  // Zachowanie dotychczasowego ID użytkownika
                .profilePhoto(candidate.getProfilePhoto())  // Zachowanie dotychczasowego zdjęcia profilowego
                .candidateSkills(candidate.getCandidateSkills())  // Zachowanie dotychczasowych umiejętności
                .candidateExperiences(candidate.getCandidateExperiences())  // Zachowanie dotychczasowych doświadczeń
                .candidateProjects(candidate.getCandidateProjects())  // Zachowanie dotychczasowych projektów
                .candidateEducationStages(candidate.getCandidateEducationStages())  // Zachowanie dotychczasowych etapów edukacji
                .candidateCourses(candidate.getCandidateCourses())  // Zachowanie dotychczasowych kursów
                .build();

        // Aktualizacja kandydata w bazie danych
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }



    @PostMapping(ADD_CANDIDATE_EXPERIENCE)
    public String addExperience(Authentication authentication,
                                @ModelAttribute("candidateExperienceDTO") CandidateExperienceDTO candidateExperienceDTO) {

        CandidateExperience candidateExperience = candidateExperienceMapper.mapToDomain(candidateExperienceDTO);
        candidateExperienceService.createExperienceData(candidateExperience, authentication);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_EXPERIENCE)
    public String updateCandidateExperience(
            @ModelAttribute CandidateExperienceDTO candidateExperienceDTO,
            Authentication authentication) throws AccessDeniedException {

        CandidateExperience candidateExperience = candidateExperienceMapper.mapToDomain(candidateExperienceDTO);
        candidateExperienceService.updateCandidateExperience(candidateExperience, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(DELETE_CANDIDATE_EXPERIENCE)
    public String deleteCandidateExperience(
            @RequestParam("candidateExperienceId") Integer candidateExperienceId
    ) {

        candidateExperienceService.deleteCandidateExperienceById(candidateExperienceId);

        return "redirect:/candidate-portal";
    }

    @PostMapping(ADD_CANDIDATE_PROJECT)
    public String addCandidateProject(
            @ModelAttribute("candidateProjectDTO") CandidateProjectDTO candidateProjectDTO) {

        CandidateProject candidateProject = candidateProjectMapper.mapToDomain(candidateProjectDTO);
        candidateProjectService.createProjectData(candidateProject);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_PROJECT)
    public String getUpdateCandidateProject(
            @ModelAttribute CandidateProjectDTO candidateProjectDTO,
            Authentication authentication) throws AccessDeniedException {

        CandidateProject candidateProject = candidateProjectMapper.mapToDomain(candidateProjectDTO);
        candidateProjectService.updateCandidateProject(candidateProject, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(DELETE_CANDIDATE_PROJECT)
    public String deleteCandidateProject(
            @RequestParam("candidateProjectId") Integer candidateProjectId) {

        candidateProjectService.deleteCandidateProjectById(candidateProjectId);

        return "redirect:/candidate-portal";
    }


    @PostMapping(ADD_CANDIDATE_EDUCATION)
    public String addCandidateEducation(
            @ModelAttribute("candidateEducationDTO") CandidateEducationDTO candidateEducationDTO) {

        CandidateEducation candidateEducation = candidateEducationMapper.mapToDomain(candidateEducationDTO);
        candidateEducationService.createEducationData(candidateEducation);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_EDUCATION)
    public String updateCandidateEducation(
            @ModelAttribute CandidateEducationDTO candidateEducationDTO,
            Authentication authentication) throws AccessDeniedException {

        CandidateEducation candidateEducation = candidateEducationMapper.mapToDomain(candidateEducationDTO);
        candidateEducationService.updateCandidateEducation(candidateEducation, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(DELETE_CANDIDATE_EDUCATION)
    public String deleteCandidateEducation(
            @RequestParam("candidateEducationId") Integer candidateEducationId) {

        candidateEducationService.deleteCandidateEducationById(candidateEducationId);

        return "redirect:/candidate-portal";
    }


    @PostMapping(ADD_CANDIDATE_COURSE)
    public String addCandidateCourse(
            @ModelAttribute("candidateCourseDTO") CandidateCourseDTO candidateCourseDTO) {

        CandidateCourse candidateCourse = candidateCourseMapper.mapToDomain(candidateCourseDTO);
        candidateCourseService.createCourseData(candidateCourse);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_COURSE)
    public String updateCandidateCourse(
            @ModelAttribute CandidateCourseDTO candidateCourseDTO,
            Authentication authentication) throws AccessDeniedException {

        CandidateCourse candidateCourse = candidateCourseMapper.mapToDomain(candidateCourseDTO);
        candidateCourseService.updateCandidateCourse(candidateCourse, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(DELETE_CANDIDATE_COURSE)
    public String deleteCandidateCourse(
            @RequestParam("candidateCourseId") Integer candidateCourseId) {

        candidateCourseService.deleteCandidateCourseById(candidateCourseId);

        return "redirect:/candidate-portal";

    }

    @PostMapping(UPDATE_CANDIDATE_HOBBY)
    public String getUpdateCandidateHobby(
            @RequestParam(value = "hobby", required = false) String hobby,
            Authentication authentication
    ) {
        // Znalezienie kandydata po e-mailu
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzenie, czy hobby nie jest puste
        if (hobby == null || hobby.trim().isEmpty()) {
            hobby = null;
        }

        // Tworzenie nowego kandydata z użyciem buildera
        candidate = new Candidate.Builder()
                .phone(candidate.getPhone())
                .status(candidate.getStatus())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .aboutMe(candidate.getAboutMe())
                .hobby(hobby)  // Nowe hobby
                .userId(candidate.getUserId())
                .profilePhoto(candidate.getProfilePhoto())
                .candidateSkills(candidate.getCandidateSkills())
                .candidateExperiences(candidate.getCandidateExperiences())
                .candidateProjects(candidate.getCandidateProjects())
                .candidateEducationStages(candidate.getCandidateEducationStages())
                .candidateCourses(candidate.getCandidateCourses())
                .build();

        // Aktualizacja kandydata w bazie danych
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }



    @PostMapping(UPDATE_CANDIDATE_SKILLS)
    public String updateCandidateSkills(
            @RequestParam(value = "candidateSkills", required = false) List<String> candidateSkills,
            Authentication authentication
    ) {
        // Znalezienie kandydata po e-mailu
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzenie, czy lista umiejętności nie jest pusta
        if (candidateSkills == null || candidateSkills.isEmpty()) {
            candidateSkills = null;
        }

        // Tworzenie nowego kandydata z użyciem buildera
        candidate = new Candidate.Builder()
                .phone(candidate.getPhone())
                .status(candidate.getStatus())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .aboutMe(candidate.getAboutMe())
                .hobby(candidate.getHobby())
                .userId(candidate.getUserId())
                .profilePhoto(candidate.getProfilePhoto())
                .candidateSkills(candidateSkills)  // Nowa lista umiejętności
                .candidateExperiences(candidate.getCandidateExperiences())
                .candidateProjects(candidate.getCandidateProjects())
                .candidateEducationStages(candidate.getCandidateEducationStages())
                .candidateCourses(candidate.getCandidateCourses())
                .build();

        // Aktualizacja kandydata w bazie danych
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }



    @PostMapping(UPDATE_CANDIDATE_ABOUT_ME)
    public String updateCandidateAboutMe(
            @RequestParam(value = "aboutMe", required = false) String aboutMe,
            Authentication authentication
    ) {
        // Znalezienie kandydata po e-mailu
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzenie, czy "aboutMe" nie jest puste
        if (aboutMe == null || aboutMe.isEmpty()) {
            aboutMe = null;
        }

        // Tworzenie nowego kandydata z użyciem buildera
        candidate = new Candidate.Builder()
                .phone(candidate.getPhone())
                .status(candidate.getStatus())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .aboutMe(aboutMe)  // Nowe "aboutMe"
                .hobby(candidate.getHobby())
                .userId(candidate.getUserId())
                .profilePhoto(candidate.getProfilePhoto())
                .candidateSkills(candidate.getCandidateSkills())
                .candidateExperiences(candidate.getCandidateExperiences())
                .candidateProjects(candidate.getCandidateProjects())
                .candidateEducationStages(candidate.getCandidateEducationStages())
                .candidateCourses(candidate.getCandidateCourses())
                .build();

        // Aktualizacja kandydata w bazie danych
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }



    @DeleteMapping(DELETE_CANDIDATE_PHOTO)
    public String deleteCandidatePhoto(Authentication authentication) {

        // Znalezienie kandydata po e-mailu
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzenie, czy istnieje zdjęcie profilowe
        if (!Objects.isNull(candidate.getProfilePhoto())) {

            // Tworzymy nowego kandydata z ustawionym profilePhoto na null za pomocą buildera
            candidate = new Candidate.Builder()
                    .phone(candidate.getPhone())
                    .status(candidate.getStatus())
                    .linkedIn(candidate.getLinkedIn())
                    .gitHub(candidate.getGitHub())
                    .techSpecialization(candidate.getTechSpecialization())
                    .aboutMe(candidate.getAboutMe())
                    .hobby(candidate.getHobby())
                    .userId(candidate.getUserId())
                    .profilePhoto(null)  // Usuwamy zdjęcie (ustawiamy na null)
                    .candidateSkills(candidate.getCandidateSkills())
                    .candidateExperiences(candidate.getCandidateExperiences())
                    .candidateProjects(candidate.getCandidateProjects())
                    .candidateEducationStages(candidate.getCandidateEducationStages())
                    .candidateCourses(candidate.getCandidateCourses())
                    .build();

            // Aktualizacja kandydata w bazie danych
            candidateService.updateCandidate(candidate, authentication);
        }

        return "redirect:/candidate-portal";
    }

}
