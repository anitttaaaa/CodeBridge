package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateCourseDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCourseMapper;
import pl.zajavka.CodeBridge.domain.CandidateCourse;

@Component
public class CandidateCourseMapperImpl implements CandidateCourseMapper {

    @Override
    public CandidateCourseDTO mapToDto(CandidateCourse candidateCourse) {
        return new CandidateCourseDTO(
                candidateCourse.getCandidateCourseId(),
                candidateCourse.getInstitution(),
                candidateCourse.getCourseTitle(),
                candidateCourse.getDescription(),
                candidateCourse.getTechnologies(),
                candidateCourse.getFromDate(),
                candidateCourse.getToDate(),
                candidateCourse.getCandidateId()
        );
    }



    @Override
    public CandidateCourse mapToDomain(CandidateCourseDTO candidateCourseDTO) {
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
