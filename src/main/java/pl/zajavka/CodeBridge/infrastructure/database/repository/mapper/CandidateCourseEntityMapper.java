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


    // Ręczne mapowanie z CandidateCourse -> CandidateCourseEntity
    default CandidateCourseEntity mapToEntity(CandidateCourse domain) {
        if (domain == null) {
            return null;
        }
        return new CandidateCourseEntity(
                domain.getCandidateCourseId(), // Przypisanie ID
                domain.getInstitution(),
                domain.getCourseTitle(),
                domain.getDescription(),
                domain.getTechnologies(),
                domain.getFromDate(),
                domain.getToDate(),
                domain.getCandidateId() // ID kandydata
        );
    }

}
