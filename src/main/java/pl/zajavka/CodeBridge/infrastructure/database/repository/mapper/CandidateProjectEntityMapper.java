package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateProject;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateProjectEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateProjectEntityMapper {

    default CandidateProject mapToDomain(CandidateProjectEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CandidateProject.Builder()
                .candidateProjectId(entity.getCandidateProjectId())
                .projectTitle(entity.getProjectTitle())
                .technologies(entity.getTechnologies())
                .description(entity.getDescription())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .projectLink(entity.getProjectLink())
                .candidateId(entity.getCandidateId())
                .build();
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

