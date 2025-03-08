package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateProject;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateProjectEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateProjectEntityMapper {

    default CandidateProject mapToDomain(CandidateProjectEntity entity) {
        return new CandidateProject(
                entity.getCandidateProjectId(),
                entity.getProjectTitle(),
                entity.getTechnologies(),
                entity.getDescription(),
                entity.getFromDate(),
                entity.getToDate(),
                entity.getProjectLink(),
                entity.getCandidateId()
        );
    }

    default CandidateProjectEntity mapToEntity(CandidateProject domain) {
        return new CandidateProjectEntity(
                domain.getCandidateProjectId(),
                domain.getProjectTitle(),
                domain.getTechnologies(),
                domain.getDescription(),
                domain.getFromDate(),
                domain.getToDate(),
                domain.getProjectLink(),
                domain.getCandidateId()
        );
    }
}

