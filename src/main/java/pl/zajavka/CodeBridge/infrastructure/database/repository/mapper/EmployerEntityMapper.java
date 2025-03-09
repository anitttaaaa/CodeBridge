package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerEntityMapper {
    default Employer mapToDomain(EmployerEntity employerEntity) {
        if (employerEntity == null) {
            return null;
        }

        // Ręczna konwersja pól
        Employer employer = new Employer.EmployerBuilder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
                .email(employerEntity.getEmail())
                .nip(employerEntity.getNip())
                .userId(employerEntity.getUserId())
                .build();


        // Możesz dodać inne pola, jeśli wymagają konwersji (np. jeśli są powiązane encje)

        return employer;
    }


    default EmployerEntity mapToEntity(Employer employer) {
        if (employer == null) {
            return null;
        }

        // Ręczna konwersja pól
        EmployerEntity employerEntity = new EmployerEntity(
                employer.getEmployerId(),
                employer.getCompanyName(),
                employer.getEmail(),
                employer.getNip(),
                employer.getUserId()
        );

        // Możesz dodać inne pola, jeśli wymagają konwersji (np. jeśli są powiązane encje)

        return employerEntity;
    }



}

