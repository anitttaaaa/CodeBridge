package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateCourseEntityMapper {

    @Mapping(target = "candidateId", source = "candidate.candidateId")
    CandidateCourse mapFromEntity(CandidateCourseEntity candidateCourseEntity);

    @Mapping(target = "candidate.candidateId", source = "candidateId")
    CandidateCourseEntity mapToEntity(CandidateCourse candidateCourse);


}
