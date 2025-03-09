package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.EmployerEntityMapper;

@Component
public class EmployerEntityMapperImpl implements EmployerEntityMapper {

    @Override
    public Employer mapToDomain(EmployerEntity employerEntity) {
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

        return employer;
    }

    @Override
    public EmployerEntity mapToEntity(Employer employer) {
        if (employer == null) {
            return null;
        }

        EmployerEntity employerEntity = new EmployerEntity(
                employer.getEmployerId(),
                employer.getCompanyName(),
                employer.getEmail(),
                employer.getNip(),
                employer.getUserId()
        );

        return employerEntity;
    }
}
