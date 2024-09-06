package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;
import pl.zajavka.CodeBridge.business.dao.EmployerDAO;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobOfferJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployerService {

    private final EmployerDAO employerDAO;
    private final JobOfferJpaRepository jobOfferJpaRepository;

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
//    public List<JobOfferEntity> findJobOffersByTechSpecialization(TechSpecializationsEnum techSpecialization) {
//        return jobOfferJpaRepository.findByTechSpecialization(techSpecialization);
//    }
}
