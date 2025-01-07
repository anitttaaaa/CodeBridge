package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateCourseDTO;
import pl.zajavka.CodeBridge.domain.CandidateCourse;

@Mapper(componentModel = "spring")
public interface CandidateCourseMapper {


    CandidateCourseDTO mapToDto(CandidateCourse candidateCourse);
    CandidateCourse mapToDomain(CandidateCourseDTO candidateCourseDTO);

}
