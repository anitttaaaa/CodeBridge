package pl.zajavka.CodeBridge.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.zajavka.CodeBridge.api.dto.CandidatePortalDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.business.CandidateService;
import pl.zajavka.CodeBridge.domain.Candidate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class CandidatePortalController {

    private static final String CANDIDATE_PORTAL = "/candidate-portal";
    private static final String UPDATE_CANDIDATE_BASIC_INFO = "/candidate-portal/update-candidate-basic-info";
    private static final String UPDATE_CANDIDATE_TECH_SPECIALIZATION = "/candidate-portal/update-candidate-tech-specialization";
    private static final String UPDATE_CANDIDATE_SKILLS = "/candidate-portal/update-candidate-skills";
    private static final String UPDATE_CANDIDATE_PHOTO = "/candidate-portal/update-candidate-photo";
    private static final String DELETE_CANDIDATE_PHOTO = "/candidate-portal/delete-candidate-photo/{email}";
    private static final String CANDIDATE_PORTAL_PROFILE_PHOTO_DISPLAY = "/candidate-portal/profilePhoto/{email}";

    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;


    // Metoda do wyświetlania podstawowych danych o kandydacie przed jakąkolwiek edycją profilu.
    @GetMapping(CANDIDATE_PORTAL)
    public String getCandidateDetails(
            @RequestParam(required = false) String email,
            Model model) {

        Candidate candidate = candidateService.findLoggedInCandidate();


        // Tworzymy DTO i dodajemy go do modelu
        CandidatePortalDTO candidateDTO = CandidatePortalDTO.builder()
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .candidateSkills(candidate.getCandidateSkills())
                .profilePhoto(candidate.getProfilePhoto())
                .build();
        model.addAttribute("candidate", candidateDTO);

        // Debugowanie - wypisz dane o kandydacie
        System.out.println("Candidate details: " + candidate + candidate.getLinkedIn() + candidate.getTechSpecialization());


        return "candidate_portal";
    }

    @GetMapping(CANDIDATE_PORTAL_PROFILE_PHOTO_DISPLAY)
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
            @RequestParam("linkedIn") String linkedIn,
            @RequestParam("gitHub") String gitHub,
//            @RequestParam("techSpecialization") String techSpecialization,
            Authentication authentication
    ) {
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());

        candidate = candidate.withName(name)
                .withSurname(surname)
                .withPhone(phone)
                .withLinkedIn(linkedIn)
                .withGitHub(gitHub);
//                .withTechSpecialization(techSpecialization);
        candidateService.updateCandidate(candidate, authentication);

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
