package pl.zajavka.CodeBridge.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.EmployerDAO;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobOfferJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.EmployerEntityMapper;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobOfferEntityMapper;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployerRepository implements EmployerDAO {

    private final EmployerJpaRepository employerJpaRepository;
    private final JobOfferJpaRepository jobOfferJpaRepository;

    private final EmployerEntityMapper employerEntityMapper;
    private final JobOfferEntityMapper jobOfferEntityMapper;


    @Override
    public Optional<Employer> findByUserId(Integer userId) {
        return employerJpaRepository.findByUserId(userId)
                .map(employerEntityMapper::mapFromEntity);
    }

    @Override
    public void createJobOffer(Employer employer) {
        EmployerEntity employerToSave = employerEntityMapper.mapToEntity(employer);
        EmployerEntity employerSaved = employerJpaRepository.saveAndFlush(employerToSave);

        employer.getJobOffers().stream()
                .filter(jobOffer -> Objects.isNull(jobOffer.getJobOfferId()))
                .map(jobOfferEntityMapper::mapToEntity)
                .forEach(jobOfferEntity -> {
                    jobOfferEntity.setEmployer(employerSaved);
                    jobOfferJpaRepository.saveAndFlush(jobOfferEntity);
                });

    }
}
