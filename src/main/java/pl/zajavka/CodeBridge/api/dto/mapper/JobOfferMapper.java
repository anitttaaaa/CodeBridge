package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.JobOfferAddRequestDTO;
import pl.zajavka.CodeBridge.domain.JobOfferFromRequest;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {

    JobOfferFromRequest mapFromDTO(JobOfferAddRequestDTO dto);



}
