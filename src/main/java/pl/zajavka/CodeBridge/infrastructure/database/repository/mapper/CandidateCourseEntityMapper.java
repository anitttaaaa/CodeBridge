package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;

public interface CandidateCourseEntityMapper {

    CandidateCourse mapFromEntity(CandidateCourseEntity entity);

    CandidateCourseEntity mapToEntity(CandidateCourse domain);
}
