package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
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
@Autowired
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

        String candidateEmail = authentication.getName();

        Integer candidateId = findCandidateByEmail(candidateEmail).getCandidateId();

        candidate = new Candidate.Builder()
                .candidateId(candidateId)
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

        candidateDAO.updateCandidate(candidate);
    }



    @Transactional
    public CandidateDTO findCandidateByCandidateId(Integer candidateId) {

        Optional<Candidate> candidate = candidateDAO.findById(candidateId);

        return candidate.map(candidateMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Candidate not found for ID: " + candidateId));
    }
}


