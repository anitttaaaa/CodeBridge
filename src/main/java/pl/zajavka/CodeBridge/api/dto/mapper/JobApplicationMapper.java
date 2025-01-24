package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {

    @Mapping(target = "candidate", expression = "java(mapCandidate(jobApplication.getCandidate()))")
    @Mapping(target = "jobOffer", expression = "java(mapJobOffer(jobApplication.getJobOffer()))")
    @Mapping(target = "employer", expression = "java(mapEmployer(jobApplication.getEmployer()))")
    JobApplicationDTO mapToDto(JobApplication jobApplication);

    default JobOfferDTO mapJobOffer(JobOffer jobOffer) {
        return JobOfferDTO.builder()
                .jobOfferId(jobOffer.getJobOfferId())
                .jobOfferTitle(jobOffer.getJobOfferTitle())
                .description(jobOffer.getDescription())
                .techSpecialization(jobOffer.getTechSpecialization())
                .city(jobOffer.getCity())
                .workType(jobOffer.getWorkType())
                .experience(jobOffer.getExperience())
                .salary(jobOffer.getSalary())
                .mustHaveSkills(jobOffer.getMustHaveSkills())
                .niceToHaveSkills(jobOffer.getNiceToHaveSkills())
                .build();
    }

    default EmployerDTO mapEmployer(Employer employer) {
        return EmployerDTO.builder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .build();
    }

    default CandidateDTO mapCandidate(Candidate candidate) {
        return CandidateDTO.builder()
                .candidateId(candidate.getCandidateId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .techSpecialization(candidate.getTechSpecialization())
                .candidateSkills(candidate.getCandidateSkills())
                .build();
    }


    @Mapping(target = "employer", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    @Mapping(target = "applicationStatus", ignore = true)
    JobApplication mapToDomain(JobApplicationDTO jobApplicationDTO);

}