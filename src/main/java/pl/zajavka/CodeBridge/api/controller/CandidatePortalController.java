package pl.zajavka.CodeBridge.api.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.enums.SkillsEnum;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;
import pl.zajavka.CodeBridge.business.*;
import pl.zajavka.CodeBridge.domain.Candidate;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

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

    public CandidatePortalController(CandidateService candidateService,
                                     CandidateExperienceService candidateExperienceService,
                                     CandidateProjectService candidateProjectService,
                                     CandidateCourseService candidateCourseService,
                                     CandidateEducationService candidateEducationService) {
        this.candidateService = candidateService;
        this.candidateExperienceService = candidateExperienceService;
        this.candidateProjectService = candidateProjectService;
        this.candidateCourseService = candidateCourseService;
        this.candidateEducationService = candidateEducationService;

    }

    @PostMapping(UPDATE_CANDIDATE_SKILLS)
    public String updateCandidateSkills(
            @RequestParam(value = "candidateSkills", required = false) List<SkillsEnum> candidateSkills,
            Authentication authentication
    ) {
        candidateService.updateCandidateSkills(authentication, candidateSkills);
        return "redirect:/candidate-portal";
    }

    @GetMapping(SHOW_CANDIDATE_PORTAL)
    public String getCandidateDetails(Model model) {

        Candidate candidate = candidateService.findLoggedInCandidate();
        CandidateDTO candidateDetailsDTO = candidateService.getSortedCandidateDetails(candidate);

        model.addAttribute("candidateExperiences", candidateDetailsDTO.getCandidateExperiences());
        model.addAttribute("candidateProjects", candidateDetailsDTO.getCandidateProjects());
        model.addAttribute("candidateEducationStages", candidateDetailsDTO.getCandidateEducationStages());
        model.addAttribute("candidateCourses", candidateDetailsDTO.getCandidateCourses());
        model.addAttribute("candidate", candidateDetailsDTO);

        return "candidate_portal";
    }


    @PostMapping(ADD_CANDIDATE_EDUCATION)
    public String addCandidateEducation(
            @ModelAttribute("candidateEducationDTO") CandidateEducationDTO candidateEducationDTO,
            Authentication authentication) {

        candidateEducationService.createEducationData(candidateEducationDTO, authentication);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_EDUCATION)
    public String updateCandidateEducation(
            @ModelAttribute CandidateEducationDTO candidateEducationDTO,
            Authentication authentication) throws AccessDeniedException {

        candidateEducationService.updateCandidateEducation(candidateEducationDTO, authentication);

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
            @ModelAttribute("candidateCourseDTO") CandidateCourseDTO candidateCourseDTO,
            Authentication authentication) {

        candidateCourseService.createCourseData(candidateCourseDTO, authentication);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_COURSE)
    public String updateCandidateCourse(
            @ModelAttribute CandidateCourseDTO candidateCourseDTO,
            Authentication authentication) throws AccessDeniedException {

        candidateCourseService.updateCandidateCourse(candidateCourseDTO, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(DELETE_CANDIDATE_COURSE)
    public String deleteCandidateCourse(
            @RequestParam("candidateCourseId") Integer candidateCourseId) {

        candidateCourseService.deleteCandidateCourseById(candidateCourseId);

        return "redirect:/candidate-portal";

    }

    @PostMapping(ADD_CANDIDATE_PROJECT)
    public String addCandidateProject(
            @ModelAttribute("candidateProjectDTO") CandidateProjectDTO candidateProjectDTO,
            Authentication authentication) {

        candidateProjectService.createProjectData(candidateProjectDTO, authentication);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_PROJECT)
    public String getUpdateCandidateProject(
            @ModelAttribute CandidateProjectDTO candidateProjectDTO,
            Authentication authentication) throws AccessDeniedException {

        candidateProjectService.updateCandidateProject(candidateProjectDTO, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(DELETE_CANDIDATE_PROJECT)
    public String deleteCandidateProject(
            @RequestParam("candidateProjectId") Integer candidateProjectId) {

        candidateProjectService.deleteCandidateProjectById(candidateProjectId);

        return "redirect:/candidate-portal";
    }

    @PostMapping(ADD_CANDIDATE_EXPERIENCE)
    public String addExperience(Authentication authentication,
                                @ModelAttribute("candidateExperienceDTO") CandidateExperienceDTO candidateExperienceDTO) throws AccessDeniedException {

        candidateExperienceService.createExperienceData(candidateExperienceDTO, authentication);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_EXPERIENCE)
    public String updateCandidateExperience(
            @ModelAttribute CandidateExperienceDTO candidateExperienceDTO,
            Authentication authentication) throws AccessDeniedException {

        candidateExperienceService.updateCandidateExperience(candidateExperienceDTO, authentication);

        return "redirect:/candidate-portal";
    }

    @PostMapping(DELETE_CANDIDATE_EXPERIENCE)
    public String deleteCandidateExperience(
            @RequestParam("candidateExperienceId") Integer candidateExperienceId
    ) {

        candidateExperienceService.deleteCandidateExperienceById(candidateExperienceId);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_TECH_SPECIALIZATION)
    public String updateCandidateTechSpecialization(
            @RequestParam(value = "techSpecialization", required = false) TechSpecializationsEnum techSpecialization,
            Authentication authentication
    ) {
        candidateService.updateCandidateTechSpecialization(authentication, techSpecialization);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_STATUS)
    public String updateCandidateStatus(
            @RequestParam(value = "status", required = false) StatusEnum status,
            Authentication authentication
    ) {
        candidateService.updateCandidateStatus(authentication, status);

        return "redirect:/candidate-portal";
    }

    @GetMapping(PROFILE_PHOTO_DISPLAY)
    public ResponseEntity<byte[]> getProfilePhoto(Authentication authentication) {
        byte[] profilePhoto = candidateService.getProfilePhoto(authentication);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(profilePhoto);
    }


    @PostMapping(UPDATE_CANDIDATE_PHOTO)
    public String updateCandidateProfilePhoto(
            @RequestParam("profilePhoto") MultipartFile profilePhoto,
            Authentication authentication
    ) throws IOException {
        candidateService.updateCandidateProfilePhoto(authentication, profilePhoto);
        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_BASIC_INFO)
    public String updateCandidateDetails(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("phone") String phone,
            @RequestParam(value = "linkedIn", required = false) String linkedIn,
            @RequestParam(value = "gitHub", required = false) String gitHub,
            Authentication authentication
    ) {
        candidateService.updateCandidateBasicInfo(authentication, name, surname, phone, linkedIn, gitHub);
        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_HOBBY)
    public String getUpdateCandidateHobby(
            @RequestParam(value = "hobby", required = false) String hobby,
            Authentication authentication
    ) {
        candidateService.updateCandidateHobby(authentication, hobby);
        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_ABOUT_ME)
    public String updateCandidateAboutMe(
            @RequestParam(value = "aboutMe", required = false) String aboutMe,
            Authentication authentication
    ) {
        candidateService.updateCandidateAboutMe(authentication, aboutMe);
        return "redirect:/candidate-portal";
    }

    @DeleteMapping(DELETE_CANDIDATE_PHOTO)
    public String deleteCandidatePhoto(Authentication authentication) {

        candidateService.deleteCandidateProfilePhoto(authentication);
        return "redirect:/candidate-portal";
    }

}


