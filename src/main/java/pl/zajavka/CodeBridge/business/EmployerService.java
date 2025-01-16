package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.EmployerDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployerService {

    private final EmployerDAO employerDAO;

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

}


