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
        return new JobOfferDTO(
                jobOffer.getJobOfferId(),
                jobOffer.getJobOfferTitle(),
                jobOffer.getDescription(),
                jobOffer.getTechSpecialization(),
                jobOffer.getWorkType(),
                jobOffer.getCity(),
                jobOffer.getExperience(),
                jobOffer.getSalary(),
                jobOffer.getMustHaveSkills(),
                jobOffer.getNiceToHaveSkills()
        );
    }

    default EmployerDTO mapEmployer(Employer employer) {
        return new EmployerDTO(
                employer.getEmployerId(),
                employer.getCompanyName(),
                employer.getEmail(),
                employer.getNip(),
                employer.getUserId()
        );
    }
    default CandidateDTO mapCandidate(Candidate candidate) {
        return new CandidateDTO.Builder()
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




