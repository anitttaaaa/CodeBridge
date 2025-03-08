package pl.zajavka.CodeBridge.business;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;

import java.util.Optional;

@Service
public class CandidateService {

    private final CandidateDAO candidateDAO;
    private final CandidateMapper candidateMapper;

    public CandidateService(CandidateDAO candidateDAO, CandidateMapper candidateMapper) {
        this.candidateDAO = candidateDAO;
        this.candidateMapper = candidateMapper;
    }

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

        // Tworzenie nowego obiektu kandydata z zaktualizowanym candidateId za pomocą Buildera
        candidate = new Candidate.Builder()
                .candidateId(candidateId)  // Ustawiamy zaktualizowany candidateId
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .status(candidate.getStatus())
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
        candidateDAO.updateCandidate(candidate);
    }



    @Transactional
    public CandidateDTO findCandidateByCandidateId(Integer candidateId) {

        Optional<Candidate> candidate = candidateDAO.findById(candidateId);

        return candidate.map(candidateMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Candidate not found for ID: " + candidateId));
    }
}


