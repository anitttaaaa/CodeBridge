package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.ApplicationsHistoryEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationsHistoryEntityMapper {

    default ApplicationsHistory mapToDomain(ApplicationsHistoryEntity entity) {
        // Mapowanie aplikacji do obiektu domenowego using Builder
        return new ApplicationsHistory.Builder()
                .applicationHistoryId(entity.getApplicationHistoryId())
                .jobOffer(mapJobOffer(entity.getJobOffer()))
                .employer(mapEmployer(entity.getEmployer()))
                .candidate(mapCandidate(entity.getCandidate()))
                .applicationStatus(entity.getApplicationStatus())
                .build();
    }


    default JobOffer mapJobOffer(JobOfferEntity jobOfferEntity) {
        // Mapowanie JobOfferEntity do JobOffer przy użyciu wzorca Builder
        Employer employer = jobOfferEntity.getEmployer() != null
                ? new Employer.EmployerBuilder()
                .employerId(jobOfferEntity.getEmployer().getEmployerId())
                .companyName(jobOfferEntity.getEmployer().getCompanyName())
                .email(jobOfferEntity.getEmployer().getEmail())
                .nip(jobOfferEntity.getEmployer().getNip())
                .userId(jobOfferEntity.getEmployer().getUserId())
                .build()
                : null;

        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferEntity.getJobOfferId())
                .jobOfferTitle(jobOfferEntity.getJobOfferTitle())
                .description(jobOfferEntity.getDescription())
                .techSpecialization(jobOfferEntity.getTechSpecialization() != null ? jobOfferEntity.getTechSpecialization().name() : null)
                .employer(employer)
                .workType(jobOfferEntity.getWorkType() != null ? jobOfferEntity.getWorkType().name() : null)
                .city(jobOfferEntity.getCity() != null ? jobOfferEntity.getCity().name() : null)
                .experience(jobOfferEntity.getExperience() != null ? jobOfferEntity.getExperience().name() : null)
                .salary(jobOfferEntity.getSalary() != null ? jobOfferEntity.getSalary().name() : null)
                .mustHaveSkills(jobOfferEntity.getMustHaveSkills())
                .niceToHaveSkills(jobOfferEntity.getNiceToHaveSkills())
                .build();
    }


    default Employer mapEmployer(EmployerEntity employerEntity) {
        return new Employer.EmployerBuilder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
                .email(employerEntity.getEmail())
                .nip(employerEntity.getNip())
                .userId(employerEntity.getUserId())
                .build();
    }


    default Candidate mapCandidate(CandidateEntity candidateEntity) {
        return new Candidate.Builder()
                .candidateId(candidateEntity.getCandidateId())
                .name(candidateEntity.getName())
                .surname(candidateEntity.getSurname())
                .email(candidateEntity.getEmail())
                .phone(candidateEntity.getPhone())
                .userId(candidateEntity.getUserId())
                .techSpecialization(candidateEntity.getTechSpecialization())
                .candidateSkills(candidateEntity.getCandidateSkills())
                .build();
    }


    // Jeśli potrzebujesz, możesz dodać mapowanie z domeny do encji.
    default ApplicationsHistoryEntity mapToEntity(ApplicationsHistory applicationsHistory) {
        return new ApplicationsHistoryEntity(
                applicationsHistory.getApplicationHistoryId(),
                mapJobOfferEntity(applicationsHistory.getJobOffer()),
                mapEmployerEntity(applicationsHistory.getEmployer()),
                mapCandidateEntity(applicationsHistory.getCandidate()),
                applicationsHistory.getApplicationStatus()
        );
    }

    default JobOfferEntity mapJobOfferEntity(JobOffer jobOffer) {
        EmployerEntity employerEntity = jobOffer.getEmployer() != null
                ? new EmployerEntity(
                jobOffer.getEmployer().getEmployerId(),
                jobOffer.getEmployer().getCompanyName(),
                jobOffer.getEmployer().getEmail(),
                jobOffer.getEmployer().getNip(),
                jobOffer.getEmployer().getUserId()
        )
                : null;

        return new JobOfferEntity(
                jobOffer.getJobOfferId(),
                jobOffer.getJobOfferTitle(),
                jobOffer.getDescription(),
                employerEntity
        );
    }



    default EmployerEntity mapEmployerEntity(Employer employer) {
        return new EmployerEntity(
                employer.getEmployerId(),
                employer.getCompanyName(),
                employer.getEmail(),
                employer.getNip(),
                employer.getUserId()
        );
    }

    default CandidateEntity mapCandidateEntity(Candidate candidate) {
        return new CandidateEntity(
                candidate.getCandidateId(),
                candidate.getName(),
                candidate.getSurname(),
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getUserId(),
                candidate.getTechSpecialization(),
                candidate.getCandidateSkills()
        );
    }
}
