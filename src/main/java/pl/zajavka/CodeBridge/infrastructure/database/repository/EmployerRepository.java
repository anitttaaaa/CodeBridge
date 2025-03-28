package pl.zajavka.CodeBridge.infrastructure.database.repository;


import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.EmployerDAO;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobOfferJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.EmployerEntityMapper;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobOfferEntityMapper;

import java.util.Optional;

@Repository
public class EmployerRepository implements EmployerDAO {

    private final EmployerJpaRepository employerJpaRepository;
    private final JobOfferJpaRepository jobOfferJpaRepository;

    private final EmployerEntityMapper employerEntityMapper;
    private final JobOfferEntityMapper jobOfferEntityMapper;

    public EmployerRepository(EmployerJpaRepository employerJpaRepository,
                              JobOfferJpaRepository jobOfferJpaRepository,
                              EmployerEntityMapper employerEntityMapper,
                              JobOfferEntityMapper jobOfferEntityMapper) {
        this.employerJpaRepository = employerJpaRepository;
        this.jobOfferJpaRepository = jobOfferJpaRepository;
        this.employerEntityMapper = employerEntityMapper;
        this.jobOfferEntityMapper = jobOfferEntityMapper;
    }

    @Override
    public Optional<Employer> findByUserId(Integer userId) {
        return employerJpaRepository.findByUserId(userId)
                .map(employerEntityMapper::mapToDomain);
    }

    @Override
    public void createJobOffer(JobOffer jobOffer) {
        JobOfferEntity jobOfferToSave = jobOfferEntityMapper.mapToEntity(jobOffer);
        JobOfferEntity jobOfferSaved = jobOfferJpaRepository.saveAndFlush(jobOfferToSave);

        jobOfferJpaRepository.saveAndFlush(jobOfferSaved);

    }

    @Override
    public Optional<Employer> findEmployerByEmail(String employerEmail) {
        return employerJpaRepository.findByEmail(employerEmail)
                .map(employerEntityMapper::mapToDomain);
    }
}
