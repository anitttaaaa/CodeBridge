package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobApplicationEntityMapper {

    JobApplicationEntity mapToEntity(JobApplication jobApplication);


    @Mapping(target = "candidate", expression = "java(mapCandidate(jobApplicationEntity.getCandidate().getCandidateId()))")
    @Mapping(target = "employer", expression = "java(mapEmployer(jobApplicationEntity.getEmployer().getEmployerId()))")
    @Mapping(target = "jobOffer", expression = "java(mapJobOffer(jobApplicationEntity.getJobOffer().getJobOfferId()))")
    JobApplication mapToDomain(JobApplicationEntity jobApplicationEntity);


    default Candidate mapCandidate(Integer candidateId) {
        // Logika mapowania Candidate na podstawie candidateId
        return Candidate.builder()
                .candidateId(candidateId)
                .build();
    }
    default Employer mapEmployer(Integer employerId) {
        // Logika mapowania Employer na podstawie employerId
        return Employer.builder()
                .employerId(employerId)
                .build();
    }
    default JobOffer mapJobOffer(Integer jobOfferId) {
        // Logika mapowania JobOffer na podstawie jobOfferId
        return JobOffer.builder()
                .jobOfferId(jobOfferId)
                .build();
    }


}
