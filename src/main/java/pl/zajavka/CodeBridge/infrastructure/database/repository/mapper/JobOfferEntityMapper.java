package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobOfferEntityMapper {

    JobOfferEntity mapToEntity(JobOffer jobOffer);

    @Mapping(target = "employer", expression = "java(mapEmployer(jobOfferEntity.getEmployer().getCompanyName(), jobOfferEntity.getEmployer().getEmployerId()))")
    JobOffer mapToDomain(JobOfferEntity jobOfferEntity);

    default Employer mapEmployer(String companyName, Integer employerId) {
        if (companyName == null || employerId == null) {
            return null;
        }
        return Employer.builder()
                .companyName(companyName)
                .employerId(employerId)
                .build();
    }
}
