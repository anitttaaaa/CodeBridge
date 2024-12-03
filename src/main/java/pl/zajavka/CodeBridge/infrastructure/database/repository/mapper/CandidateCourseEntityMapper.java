package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateCourseEntityMapper {

    CandidateCourse mapFromEntity(CandidateCourseEntity candidateCourseEntity);

    CandidateCourseEntity mapToEntity(CandidateCourse candidateCourse);
}
