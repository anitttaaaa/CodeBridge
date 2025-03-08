package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CandidateService {

    private final CandidateDAO candidateDAO;
    private final CandidateMapper candidateMapper;


    @Transactional(readOnly = true)
    public Candidate findLoggedInCandidate() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findCandidateByEmail(email);
    }

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

        // Pobranie e-maila kandydata z uwierzytelnienia
        String candidateEmail = authentication.getName();

        // Znalezienie identyfikatora kandydata po e-mailu
        Integer candidateId = findCandidateByEmail(candidateEmail).getCandidateId();

        // Tworzenie nowego obiektu kandydata z zaktualizowanym candidateId
        candidate = new Candidate(
                candidateId, // Ustawiamy zaktualizowany candidateId
                candidate.getName(),
                candidate.getSurname(),
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getStatus(),
                candidate.getLinkedIn(),
                candidate.getGitHub(),
                candidate.getTechSpecialization(),
                candidate.getAboutMe(),
                candidate.getHobby(),
                candidate.getUserId(),
                candidate.getProfilePhoto(),
                candidate.getCandidateSkills(),
                candidate.getCandidateExperiences(),
                candidate.getCandidateProjects(),
                candidate.getCandidateEducationStages(),
                candidate.getCandidateCourses()
        );

        // Aktualizacja kandydata w bazie danych
        candidateDAO.updateCandidate(candidate);
    }


    @Transactional
    public CandidateDTO findCandidateByCandidateId(Integer candidateId) {

        Optional<Candidate> candidate = candidateDAO.findById(candidateId);

        return candidate.map(candidateMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Candidate not found for ID: " + candidateId));
    }
}


