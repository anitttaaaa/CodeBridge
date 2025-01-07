package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.domain.JobOffer;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {

    JobOffer mapToDomain(JobOfferDTO dto);

    JobOfferDTO mapToDTO(JobOffer jobOffer);


}
