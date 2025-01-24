package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.CodeBridge.api.dto.ApplicationsHistoryDTO;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;

@Mapper(componentModel = "spring")
public interface ApplicationsHistoryMapper {


    @Mapping(target = "candidate", expression = "java(mapCandidate(applicationsHistory.getCandidate()))")
    @Mapping(target = "jobOffer", expression = "java(mapJobOffer(applicationsHistory.getJobOffer()))")
    @Mapping(target = "employer", expression = "java(mapEmployer(applicationsHistory.getEmployer()))")
    ApplicationsHistoryDTO mapToDto(ApplicationsHistory applicationsHistory);

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



}
