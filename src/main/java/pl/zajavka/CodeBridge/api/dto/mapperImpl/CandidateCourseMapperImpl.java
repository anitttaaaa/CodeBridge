package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateCourseDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCourseMapper;
import pl.zajavka.CodeBridge.domain.CandidateCourse;

@Component
public class CandidateCourseMapperImpl implements CandidateCourseMapper {

    @Override
    public CandidateCourseDTO mapToDto(CandidateCourse candidateCourse) {
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
