package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.business.dao.EmployerDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployerService {

    private final EmployerDAO employerDAO;
    private final CandidateDAO candidateDAO;

    public EmployerService(EmployerDAO employerDAO, CandidateDAO candidateDAO) {
        this.employerDAO = employerDAO;
        this.candidateDAO = candidateDAO;
    }

    @Transactional
    public Employer findEmployer(Integer userId) {
        Optional<Employer> employer = employerDAO.findByUserId(userId);
        if (employer.isEmpty()) {
            throw new NotFoundException("Could not find employer by user id: [%s]".formatted(userId));
        }
        return employer.get();
    }

    @Transactional
    public void createJobOffer(Employer employerAddJobOffer) {
        employerDAO.createJobOffer(employerAddJobOffer);

    }

    @Transactional
    public Employer findEmployerByEmail(String employerEmail) {
        Optional<Employer> employerByEmail = employerDAO.findEmployerByEmail(employerEmail);
        if (employerByEmail.isEmpty()) {
            throw new NotFoundException("Could not find employer by email: [%s]".formatted(employerByEmail));
        }
        return employerByEmail.get();
    }

    public List<Candidate> getFilteredCandidates(
            String techSpecialization,
            String status) {

        List<Candidate> allCandidates = candidateDAO.findAll();


        List<Candidate> collect = allCandidates.stream()
                .filter(candidate -> techSpecialization == null || techSpecialization.equals(candidate.getTechSpecialization()))
                .filter(candidate -> status == null || status.equals(candidate.getStatus()))
                .collect(Collectors.toList());

        return collect;
    }

    @Transactional
    public List<Candidate> getAllCandidates() {
        return candidateDAO.findAllCandidates();
    }


    @Transactional(readOnly = true)
    public Employer findLoggedInEmployer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findEmployerByEmail(email);
    }
}



