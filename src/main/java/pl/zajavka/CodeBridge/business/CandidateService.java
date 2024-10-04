package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CandidateService {

    private final CandidateDAO candidateDAO;
    private final CodeBridgeUserDetailsService codeBridgeUserDetailsService;

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


}

