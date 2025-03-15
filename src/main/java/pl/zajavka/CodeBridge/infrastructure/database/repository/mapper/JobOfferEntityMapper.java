package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;

public interface JobOfferEntityMapper {

    JobOffer mapToDomain(JobOfferEntity jobOfferEntity);

    JobOfferEntity mapToEntity(JobOffer jobOffer);
}
