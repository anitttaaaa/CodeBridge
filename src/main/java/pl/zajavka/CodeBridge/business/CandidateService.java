package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateExperienceMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidateService {

    private final CandidateDAO candidateDAO;

    private final CandidateExperienceMapper candidateExperienceMapper;


    // Metoda wyszukuje zalogowanego użytkownika i wykorzystuje
    // findCandidateByEmail zeby dostać sie do szczegółów
    @Transactional(readOnly = true)
    public Candidate findLoggedInCandidate() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findCandidateByEmail(email);

    }

    // Metoda wyszukuje kandydata po emailu, jego wszytskie informacje.
    @Transactional
    public Candidate findCandidateByEmail(String email) {
        Optional<Candidate> candidateByEmail = candidateDAO.findCandidateByEmail(email);
        if (candidateByEmail.isEmpty()) {
            throw new NotFoundException("Could not find candidate by email: [%s]".formatted(email));
        }
        return candidateByEmail.get();
    }


    @Transactional
    public void updateCandidate(Candidate candidate, Authentication authentication) {
        candidateDAO.updateCandidate(candidate);
    }

}


