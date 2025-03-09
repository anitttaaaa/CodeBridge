package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.CandidateCourseDTO;
import pl.zajavka.CodeBridge.domain.CandidateCourse;

public interface CandidateCourseMapper {

    CandidateCourseDTO mapToDto(CandidateCourse candidateCourse);

    CandidateCourse mapToDomain(CandidateCourseDTO candidateCourseDTO);
}
