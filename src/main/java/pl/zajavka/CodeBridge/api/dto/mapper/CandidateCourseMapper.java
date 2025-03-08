package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateCourseDTO;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
@Mapper(componentModel = "spring")
public interface CandidateCourseMapper {

    default CandidateCourseDTO mapToDto(CandidateCourse candidateCourse) {
        return new CandidateCourseDTO.Builder()
                .candidateCourseId(candidateCourse.getCandidateCourseId())
                .institution(candidateCourse.getInstitution())
                .courseTitle(candidateCourse.getCourseTitle())
                .description(candidateCourse.getDescription())
                .technologies(candidateCourse.getTechnologies())
                .fromDate(candidateCourse.getFromDate())
                .toDate(candidateCourse.getToDate())
                .candidateId(candidateCourse.getCandidateId())
                .build();
    }

    default CandidateCourse mapToDomain(CandidateCourseDTO candidateCourseDTO) {
        return new CandidateCourse.Builder()
                .candidateCourseId(candidateCourseDTO.getCandidateCourseId())
                .institution(candidateCourseDTO.getInstitution())
                .courseTitle(candidateCourseDTO.getCourseTitle())
                .description(candidateCourseDTO.getDescription())
                .technologies(candidateCourseDTO.getTechnologies())
                .fromDate(candidateCourseDTO.getFromDate())
                .toDate(candidateCourseDTO.getToDate())
                .candidateId(candidateCourseDTO.getCandidateId())
                .build();
    }
}
