package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @Mapping(target = "candidate", expression = "java(mapCandidate(applicationsHistoryEntity.getCandidate()))")
    @Mapping(target = "employer", expression = "java(mapEmployer(applicationsHistoryEntity.getEmployer()))")
    @Mapping(target = "jobOffer", expression = "java(mapJobOffer(applicationsHistoryEntity.getJobOffer()))")
    ApplicationsHistory mapToDomain(ApplicationsHistoryEntity applicationsHistoryEntity);


    default JobOffer mapJobOffer(JobOfferEntity jobOfferEntity) {
        // Konwersja EmployerEntity na Employer
        Employer employer = jobOfferEntity.getEmployer() != null
                ? new Employer(
                jobOfferEntity.getEmployer().getEmployerId(),
                jobOfferEntity.getEmployer().getCompanyName(),
                jobOfferEntity.getEmployer().getEmail(),
                jobOfferEntity.getEmployer().getNip(),
                jobOfferEntity.getEmployer().getUserId()
        )
                : null;

        // Tworzymy nowy obiekt JobOffer przy użyciu konstruktora
        return new JobOffer(
                jobOfferEntity.getJobOfferId(),
                jobOfferEntity.getJobOfferTitle(),
                jobOfferEntity.getDescription(),
                jobOfferEntity.getTechSpecialization() != null ? jobOfferEntity.getTechSpecialization().name() : null,
                employer,  // Używamy zamapowanego obiektu Employer
                jobOfferEntity.getWorkType() != null ? jobOfferEntity.getWorkType().name() : null,
                jobOfferEntity.getCity() != null ? jobOfferEntity.getCity().name() : null,
                jobOfferEntity.getExperience() != null ? jobOfferEntity.getExperience().name() : null,
                jobOfferEntity.getSalary() != null ? jobOfferEntity.getSalary().name() : null,
                jobOfferEntity.getMustHaveSkills(),
                jobOfferEntity.getNiceToHaveSkills()
        );
    }

    default Employer mapEmployer(EmployerEntity employerEntity) {
        return Employer.builder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
                .build();

    }
    default Candidate mapCandidate(CandidateEntity candidateEntity) {
        return new Candidate(
                candidateEntity.getCandidateId(),
                candidateEntity.getName(),
                candidateEntity.getSurname(),
                candidateEntity.getEmail(),
                candidateEntity.getPhone(),
                candidateEntity.getUserId(),
                candidateEntity.getTechSpecialization(),
                candidateEntity.getCandidateSkills()
        );
    }

    ApplicationsHistoryEntity mapToEntity(ApplicationsHistory applicationsHistory);

}
