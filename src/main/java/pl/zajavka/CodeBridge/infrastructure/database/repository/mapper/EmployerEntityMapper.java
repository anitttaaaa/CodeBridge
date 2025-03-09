package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;

public interface EmployerEntityMapper {

    Employer mapToDomain(EmployerEntity employerEntity);

    EmployerEntity mapToEntity(Employer employer);
}
