package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.domain.JobOffer;

public interface JobOfferMapper {

    JobOffer mapToDomain(JobOfferDTO jobOfferDTO);

    JobOfferDTO mapToDTO(JobOffer jobOffer);
}
