package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.zajavka.CodeBridge.api.dto.CandidatePortalDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.CandidateRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateEntityMapper;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CandidateService {

    private final CandidateDAO candidateDAO;
    private final CodeBridgeUserDetailsService codeBridgeUserDetailsService;

    private final ResourceLoader resourceLoader;

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private CandidateEntityMapper candidateEntityMapper;

    // Ta metoda wyszukuje kandydata po emailu, jego wszytskie informacje
    @Transactional
    public Candidate findCandidateByEmail(String email) {
        Optional<Candidate> candidateByEmail = candidateDAO.findCandidateByEmail(email);
        if (candidateByEmail.isEmpty()) {
            throw new NotFoundException("Could not find candidate by email: [%s]".formatted(email));
        }
        return candidateByEmail.get();
    }


    // Metoda do znalezienia emaila na podstawie zalogowanego użytkownika
    @Transactional(readOnly = true)
    public Candidate findLoggedInCandidate() {
        // Pobieramy aktualnego użytkownika z kontekstu bezpieczeństwa
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Uzyskujemy e-mail zalogowanego użytkownika
            String email = authentication.getName();
            // Używamy istniejącej metody do wyszukiwania kandydata
            return findCandidateByEmail(email);
        } else {
            throw new IllegalStateException("No authenticated user found");
        }
    }


    @Transactional
    public void updateCandidatePhoto(Candidate candidate, Authentication authentication) {
        // 1. Sprawdzenie uprawnień
        String loggedInUserEmail = authentication.getName();

        System.out.println("Logged in user email: " + loggedInUserEmail);
        System.out.println("Candidate email: " + candidate.getEmail());

        if (!candidate.getEmail().equals(loggedInUserEmail)) {
            throw new RuntimeException("You do not have permission to update this candidate.");
        }

        // 2. Zapisz zaktualizowanego kandydata
        candidateDAO.updateCandidatePhoto(candidate);
    }

    public byte[] getCandidatePhoto(String email, Authentication authentication) {

        String loggedInUserEmail = authentication.getName();

        if (!email.equals(loggedInUserEmail)) {
            throw new RuntimeException("You do not have permission to view this photo.");
        }

        // Wyszukujemy kandydata po emailu
        Candidate candidate = candidateRepository.findCandidateByEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        byte[] photo = candidate.getProfilePhoto();
        if (photo == null) {
            try {
               File file= new File("C:/Users/anita/IdeaProjects/CodeBridge/src/main/resources/static/images/avatar.PNG");

                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Default avatar not found", e);
            }
        }
        return photo;
    }
}

