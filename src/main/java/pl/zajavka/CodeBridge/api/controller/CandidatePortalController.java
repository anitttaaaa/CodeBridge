package pl.zajavka.CodeBridge.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateExperienceMapper;
import pl.zajavka.CodeBridge.business.CandidateExperienceService;
import pl.zajavka.CodeBridge.business.CandidateService;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CandidatePortalController {

    private static final String SHOW_CANDIDATE_PORTAL = "/candidate-portal";
    private static final String PROFILE_PHOTO_DISPLAY = "/candidate-portal/profilePhoto/{email}";
    private static final String SHOW_CANDIDATE_EXPERIENCE = "/candidate-portal/show-experience/{email}";

    private static final String UPDATE_CANDIDATE_BASIC_INFO = "/candidate-portal/update-candidate-basic-info";
    private static final String UPDATE_CANDIDATE_TECH_SPECIALIZATION = "/candidate-portal/update-candidate-tech-specialization";
    private static final String UPDATE_CANDIDATE_SKILLS = "/candidate-portal/update-candidate-skills";
    private static final String UPDATE_CANDIDATE_ABOUT_ME = "/candidate-portal/update-candidate-about-me";
    private static final String UPDATE_CANDIDATE_HOBBY = "/candidate-portal/update-candidate-hobby";
    private static final String UPDATE_CANDIDATE_EXPERIENCE = "/candidate-portal/candidate-experience";
    private static final String UPDATE_CANDIDATE_PHOTO = "/candidate-portal/update-candidate-photo";

    private static final String DELETE_CANDIDATE_PHOTO = "/candidate-portal/delete-candidate-photo/{email}";



    private final CandidateService candidateService;
    private final CandidateExperienceService candidateExperienceService;
    private final CandidateExperienceMapper candidateExperienceMapper;


//    @GetMapping(SHOW_CANDIDATE_EXPERIENCE)
//    public List<CandidateExperienceDTO> getCandidateExperience(Authentication authentication) {
//        return candidateExperienceService.getExperienceData(authentication);
//    }



    @GetMapping(SHOW_CANDIDATE_PORTAL)
    public String getCandidateDetails(
            @RequestParam(required = false) String email,
            Model model) {

        // Pobranie pełnego obiektu kandydata
        Candidate candidate = candidateService.findLoggedInCandidate();

        List<CandidateExperience> candidateExperienceList = candidateExperienceService.findExperienceByCandidateId(candidate.getCandidateId());
        List<CandidateExperienceDTO> candidateExperienceDTOList = candidateExperienceList.stream()
                .map(candidateExperienceMapper::mapToDto).toList();

        // Dodanie całego obiektu kandydata do modelu
        model.addAttribute("candidate", candidate);
        model.addAttribute("candidateExperienceDTOList", candidateExperienceDTOList);

        return "candidate_portal";
    }



    @GetMapping(PROFILE_PHOTO_DISPLAY)
    public ResponseEntity<byte[]> getProfilePhoto(
            @PathVariable("email") String email,
            Authentication authentication) {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        byte[] profilePhoto = candidate.getProfilePhoto();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(profilePhoto);

    }


    @PostMapping(UPDATE_CANDIDATE_PHOTO)
    public String updateCandidateProfilePhoto(
            @RequestParam("profilePhoto") MultipartFile profilePhoto,
            Authentication authentication

    ) throws IOException {
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());


        if (!profilePhoto.isEmpty()) {
            byte[] profilePhotoData = profilePhoto.getBytes();
            candidate = candidate.withProfilePhoto(profilePhotoData);
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
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        candidate = candidate.withName(name)
                .withSurname(surname)
                .withPhone(phone)
                .withLinkedIn(linkedIn)
                .withGitHub(gitHub);
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_EXPERIENCE)
    public String addExperience(
            @ModelAttribute("candidateExperienceDTO") CandidateExperienceDTO candidateExperienceDTO,
            Authentication authentication) {
        CandidateExperience candidateExperience = candidateExperienceMapper.mapFromDTO(candidateExperienceDTO);
        candidateExperienceService.createExperienceData(candidateExperience, authentication);


        return "redirect:/candidate-portal";
    }

    @PostMapping(UPDATE_CANDIDATE_TECH_SPECIALIZATION)
    public String updateCandidateTechSpecialization(
            @RequestParam(value = "techSpecialization", required = false) String techSpecialization,
            Authentication authentication
    ) {
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzanie, czy techSpecialization jest null lub pustym ciągiem
        if (techSpecialization == null || techSpecialization.trim().isEmpty()) {
            techSpecialization = null; // Ustawiamy na null, jeśli nie wybrano specjalizacji
        }

        candidate = candidate.withTechSpecialization(techSpecialization);
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(UPDATE_CANDIDATE_HOBBY)
    public String getUpdateCandidateHobby(
            @RequestParam(value = "hobby", required = false) String hobby,
            Authentication authentication
    ) {
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzanie, czy techSpecialization jest null lub pustym ciągiem
        if (hobby == null || hobby.trim().isEmpty()) {
            hobby = null; // Ustawiamy na null, jeśli nie wybrano specjalizacji
        }

        candidate = candidate.withHobby(hobby);
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(UPDATE_CANDIDATE_SKILLS)
    public String updateCandidateSkills(
            @RequestParam(value = "candidateSkills", required = false) List<String> candidateSkills,
            Authentication authentication
    ) {
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzanie, czy candidateSkills jest null lub pusta
        if (candidateSkills == null || candidateSkills.isEmpty()) {
            candidateSkills = null; // Ustawiamy na null, jeśli nie wybrano umiejętności
        }

        candidate = candidate.withCandidateSkills(candidateSkills);
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }


    @PostMapping(UPDATE_CANDIDATE_ABOUT_ME)
    public String updateCandidateAboutMe(
            @RequestParam(value = "aboutMe", required = false) String aboutMe,
            Authentication authentication
    ) {
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        // Sprawdzanie, czy candidateSkills jest null lub pusta
        if (aboutMe == null || aboutMe.isEmpty()) {
            aboutMe = null; // Ustawiamy na null, jeśli nie wybrano umiejętności
        }

        candidate = candidate.withAboutMe(aboutMe);
        candidateService.updateCandidate(candidate, authentication);

        return "redirect:/candidate-portal";
    }


    @DeleteMapping(DELETE_CANDIDATE_PHOTO)
    public String deleteCandidatePhoto(
            Authentication authentication) {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        if (!Objects.isNull(candidate.getProfilePhoto())) {

            candidate = candidate.withProfilePhoto(null);
            candidateService.updateCandidate(candidate, authentication);

        }

        return "redirect:/candidate-portal";
    }


}
