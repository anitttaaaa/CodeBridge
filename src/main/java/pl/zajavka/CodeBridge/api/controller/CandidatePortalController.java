package pl.zajavka.CodeBridge.api.controller;


import lombok.RequiredArgsConstructor;
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

@Controller
@RequiredArgsConstructor
public class CandidatePortalController {

    private static final String CANDIDATE_PORTAL = "/candidate-portal";
    private static final String UPDATE_CANDIDATE_ALL_DETAILS = "/candidate-portal/update-candidate";
    private static final String UPDATE_CANDIDATE_PHOTO = "/update-candidate-photo";
    private static final String CANDIDATE_PORTAL_PROFILE_PHOTO_DISPLAY = "/candidate-portal/profilePhoto/{email}";
    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;


// Metoda do wyświetlania podstawowych danych o kandydacie przed jakąkolwiek edycją profilu.
    @GetMapping(CANDIDATE_PORTAL)
    public String getCandidateDetails(@RequestParam(required = false) String email, Model model) {
        Candidate candidate = candidateService.findLoggedInCandidate();

        // Tworzymy DTO i dodajemy go do modelu
        CandidatePortalDTO candidateDTO = CandidatePortalDTO.builder()
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .build();
        model.addAttribute("candidate", candidateDTO);

        return "candidate_portal";
    }
//
    @PostMapping(UPDATE_CANDIDATE_PHOTO)
    public String updateCandidateProfilePhoto(
            @RequestParam("profilePhoto") MultipartFile profilePhoto,
            Authentication authentication

    ) throws IOException {
        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());


        if (!profilePhoto.isEmpty()) {
            byte[] profilePhotoData = profilePhoto.getBytes();
            candidate = candidate.withProfilePhoto(profilePhotoData);
            candidateService.updateCandidatePhoto(candidate, authentication);
        }


        return "redirect:/candidate-portal";
    }

    @GetMapping(CANDIDATE_PORTAL_PROFILE_PHOTO_DISPLAY)
    public ResponseEntity<byte[]> getCandidateProfilePhoto(
            @PathVariable String email,
            Authentication authentication) {

        byte[] profilePhoto = candidateService.getCandidatePhoto(email,authentication);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(profilePhoto);
    }





}
