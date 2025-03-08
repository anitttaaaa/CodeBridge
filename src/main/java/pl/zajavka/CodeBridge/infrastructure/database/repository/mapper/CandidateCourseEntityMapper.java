package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateCourseEntityMapper {

    // Ręczne mapowanie z CandidateCourseEntity -> CandidateCourse
    default CandidateCourse mapFromEntity(CandidateCourseEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CandidateCourse(
                entity.getCandidateCourseId(), // Przypisanie ID
                entity.getInstitution(),
                entity.getCourseTitle(),
                entity.getDescription(),
                entity.getTechnologies(),
                entity.getFromDate(),
                entity.getToDate(),
                entity.getCandidateId() // ID kandydata
        );
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
