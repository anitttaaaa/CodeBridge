package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.JobOfferDAO;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobOfferJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobOfferEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class JobOfferRepository implements JobOfferDAO {

    private final JobOfferJpaRepository jobOfferJpaRepository;
    private final JobOfferEntityMapper jobOfferEntityMapper;


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


}
