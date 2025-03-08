package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.JobOfferDAO;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobOfferJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobOfferEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JobOfferRepository implements JobOfferDAO {

    private final JobOfferJpaRepository jobOfferJpaRepository;
    private final JobOfferEntityMapper jobOfferEntityMapper;

    public JobOfferRepository(JobOfferJpaRepository jobOfferJpaRepository,
                              JobOfferEntityMapper jobOfferEntityMapper) {
        this.jobOfferJpaRepository = jobOfferJpaRepository;
        this.jobOfferEntityMapper = jobOfferEntityMapper;
    }

    @Override
    public List<JobOffer> findAllJobOffers() {

        List<JobOffer> jobOffers = jobOfferJpaRepository.findAll().stream()
                .map(jobOfferEntityMapper::mapToDomain)
                .collect(Collectors.toList());

        return jobOffers;
    }

    @Override
    public List<JobOffer> findAll() {
        List<JobOfferEntity> jobOfferEntities = jobOfferJpaRepository.findAll();

        return jobOfferEntities.stream().
                map(jobOfferEntityMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<JobOffer> findById(Integer jobOfferId) {
        return jobOfferJpaRepository.findById(jobOfferId)
                .map(jobOfferEntityMapper::mapToDomain);    }

    @Override
    public List<JobOffer> findJobOffersByEmployerId(Integer employerId) {
        List<JobOfferEntity> jobOfferEntities= jobOfferJpaRepository.findJobOffersByEmployerId(employerId);

        return jobOfferEntities.stream()
                .map(jobOfferEntityMapper::mapToDomain)
                .collect(Collectors.toList());
    }


}
