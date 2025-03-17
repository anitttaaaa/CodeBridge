package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateCourseEntityMapper;

@Component
public class CandidateCourseEntityMapperImpl implements CandidateCourseEntityMapper {

    @Override
    public CandidateCourse mapToDomain(CandidateCourseEntity entity, Integer candidateId) {
        if (entity == null) {
            return null;
        }

        return new CandidateCourse.Builder()
                .candidateCourseId(entity.getCandidateCourseId())
                .institution(entity.getInstitution())
                .courseTitle(entity.getCourseTitle())
                .description(entity.getDescription())
                .technologies(entity.getTechnologies())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .candidateId(candidateId)
                .build();
    }

    @Override
    public CandidateCourseEntity mapToEntity(CandidateCourse domain, Integer candidateId) {
        return new CandidateCourseEntity.Builder()
                .candidateCourseId(domain.getCandidateCourseId())
                .institution(domain.getInstitution())
                .courseTitle(domain.getCourseTitle())
                .description(domain.getDescription())
                .technologies(domain.getTechnologies())
                .fromDate(domain.getFromDate())
                .toDate(domain.getToDate())
                .candidateId(candidateId)
                .build();
    }

}
