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

    // Mapowanie z employer na tylko companyName
    @Mapping(target = "employer", expression = "java(mapEmployer(jobOfferEntity.getEmployer().getCompanyName()))")
    JobOffer mapToDomain(JobOfferEntity jobOfferEntity);

    // Metoda pomocnicza, która tworzy obiekt Employer z companyName
    default Employer mapEmployer(String companyName) {
        if (companyName == null) {
            return null;
        }
        return Employer.builder().companyName(companyName).build();  // Tworzymy Employer tylko z companyName
    }
}
