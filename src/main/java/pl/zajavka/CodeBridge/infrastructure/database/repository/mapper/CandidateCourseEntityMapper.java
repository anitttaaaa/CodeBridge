package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;

public interface CandidateCourseEntityMapper {

    CandidateCourse mapToDomain(CandidateCourseEntity entity, Integer candidateId);

    CandidateCourseEntity mapToEntity(CandidateCourse domain, Integer candidateId);
}
