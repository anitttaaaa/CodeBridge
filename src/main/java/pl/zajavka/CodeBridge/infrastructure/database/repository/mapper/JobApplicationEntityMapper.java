package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobApplicationEntityMapper {

    JobApplicationEntity mapToEntity(JobApplication jobApplication);

    @Mapping(target = "candidate", expression = "java(mapCandidate(jobApplicationEntity.getCandidate()))")
    @Mapping(target = "employer", expression = "java(mapEmployer(jobApplicationEntity.getEmployer()))")
    @Mapping(target = "jobOffer", expression = "java(mapJobOffer(jobApplicationEntity.getJobOffer()))")
    JobApplication mapToDomain(JobApplicationEntity jobApplicationEntity);

    default JobOffer mapJobOffer(JobOfferEntity jobOfferEntity) {
        // Mapowanie pól z JobOfferEntity na JobOffer
        return JobOffer.builder()
                .jobOfferId(jobOfferEntity.getJobOfferId())
                .jobOfferTitle(jobOfferEntity.getJobOfferTitle())
                .description(jobOfferEntity.getDescription())
                .techSpecialization(jobOfferEntity.getTechSpecialization().name())
                .city(jobOfferEntity.getCity().name())
                .workType(jobOfferEntity.getWorkType().name())
                .experience(jobOfferEntity.getExperience().name())
                .salary(jobOfferEntity.getSalary().name())
                .mustHaveSkills(jobOfferEntity.getMustHaveSkills())
                .niceToHaveSkills(jobOfferEntity.getNiceToHaveSkills())
                .build();
    }

    default Employer mapEmployer(EmployerEntity employerEntity) {
        return Employer.builder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
                .build();
    }

    default Candidate mapCandidate(CandidateEntity candidateEntity) {
        return Candidate.builder()
                .candidateId(candidateEntity.getCandidateId())
                .userId(candidateEntity.getUserId())
                .name(candidateEntity.getName())
                .surname(candidateEntity.getSurname())
                .email(candidateEntity.getEmail())
                .phone(candidateEntity.getPhone())
                .techSpecialization(candidateEntity.getTechSpecialization())
                .candidateSkills(candidateEntity.getCandidateSkills())
                .build();
    }
}
