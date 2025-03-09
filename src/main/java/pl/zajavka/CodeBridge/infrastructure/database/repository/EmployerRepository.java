package pl.zajavka.CodeBridge.infrastructure.database.repository;


import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.EmployerDAO;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobOfferJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.EmployerEntityMapper;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobOfferEntityMapper;

import java.util.Objects;
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
    public void createJobOffer(Employer employer) {
        // Mapowanie do encji Employer
        EmployerEntity employerToSave = employerEntityMapper.mapToEntity(employer);
        EmployerEntity employerSaved = employerJpaRepository.saveAndFlush(employerToSave);

        // Mapowanie ofert pracy i przypisanie pracodawcy
        employer.getJobOffers().stream()
                .filter(jobOffer -> Objects.isNull(jobOffer.getJobOfferId()))
                .map(jobOffer -> jobOfferEntityMapper.mapToEntity(jobOffer))
                .forEach(jobOfferEntity -> {
                    // Tworzenie nowej encji JobOffer z przypisanym pracodawcą
                    JobOfferEntity jobOfferEntityWithEmployer = new JobOfferEntity.Builder()
                            .jobOfferId(jobOfferEntity.getJobOfferId())
                            .jobOfferTitle(jobOfferEntity.getJobOfferTitle())
                            .description(jobOfferEntity.getDescription())
                            .employer(employerSaved) // przypisanie pracodawcy do oferty
                            .build();

                    // Zapisz ofertę pracy w repozytorium
                    jobOfferJpaRepository.saveAndFlush(jobOfferEntityWithEmployer);
                });
    }


    @Override
    public Optional<Employer> findEmployerByEmail(String employerEmail) {
        return employerJpaRepository.findByEmail(employerEmail)
                .map(employerEntityMapper::mapToDomain);
    }
}
