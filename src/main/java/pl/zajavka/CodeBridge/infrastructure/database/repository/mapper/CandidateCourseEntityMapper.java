package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateCourseEntityMapper {

    default CandidateCourse mapFromEntity(CandidateCourseEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CandidateCourse.Builder()
                .candidateCourseId(entity.getCandidateCourseId()) // Przypisanie ID
                .institution(entity.getInstitution())
                .courseTitle(entity.getCourseTitle())
                .description(entity.getDescription())
                .technologies(entity.getTechnologies())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .candidateId(entity.getCandidateId()) // ID kandydata
                .build();
    }


    default CandidateCourseEntity mapToEntity(CandidateCourse domain) {
        if (domain == null) {
            return null;
        }

        // Zastosowanie wzorca builder do mapowania
        return new CandidateCourseEntity.Builder()
                .candidateCourseId(domain.getCandidateCourseId())  // Przypisanie ID
                .institution(domain.getInstitution())
                .courseTitle(domain.getCourseTitle())
                .description(domain.getDescription())
                .technologies(domain.getTechnologies())
                .fromDate(domain.getFromDate())
                .toDate(domain.getToDate())
                .candidateId(domain.getCandidateId()) // ID kandydata
                .build();
    }


}
