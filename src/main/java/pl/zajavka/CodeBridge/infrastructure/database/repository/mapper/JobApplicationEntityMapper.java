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

    // Ręczne mapowanie JobApplication -> JobApplicationEntity
    default JobApplicationEntity mapToEntity(JobApplication jobApplication) {
        // Mapowanie JobOffer -> JobOfferEntity
        JobOfferEntity jobOfferEntity = new JobOfferEntity(
                jobApplication.getJobOffer().getJobOfferId(),
                jobApplication.getJobOffer().getJobOfferTitle(),
                jobApplication.getJobOffer().getDescription(),
                jobApplication.getJobOffer().getMustHaveSkills(),
                jobApplication.getJobOffer().getNiceToHaveSkills()
        );

        // Mapowanie Employer -> EmployerEntity
        EmployerEntity employerEntity = mapEmployerToEntity(jobApplication.getEmployer());

        // Mapowanie Candidate -> CandidateEntity
        CandidateEntity candidateEntity = mapCandidateToEntity(jobApplication.getCandidate());

        // Tworzymy nowy obiekt JobApplicationEntity przy użyciu konstruktora
        return new JobApplicationEntity(
                jobApplication.getApplicationId(),
                jobOfferEntity, // Dodajemy mapowany JobOfferEntity
                employerEntity, // Dodajemy mapowanego EmployerEntity
                candidateEntity, // Dodajemy mapowanego CandidateEntity
                jobApplication.getApplicationStatus() // Dodajemy status aplikacji
        );
    }


    // Ręczne mapowanie JobApplicationEntity -> JobApplication
    default JobApplication mapToDomain(JobApplicationEntity jobApplicationEntity) {
        // Mapowanie JobOfferEntity -> JobOffer
        JobOffer jobOffer = new JobOffer(
                jobApplicationEntity.getJobOffer().getJobOfferId(),
                jobApplicationEntity.getJobOffer().getJobOfferTitle(),
                jobApplicationEntity.getJobOffer().getDescription(),
                jobApplicationEntity.getJobOffer().getTechSpecialization().name(),
                mapEmployer(jobApplicationEntity.getJobOffer().getEmployer()), // Employer mapowany z JobOffer
                jobApplicationEntity.getJobOffer().getWorkType().name(),
                jobApplicationEntity.getJobOffer().getCity().name(),
                jobApplicationEntity.getJobOffer().getExperience().name(),
                jobApplicationEntity.getJobOffer().getSalary().name(),
                jobApplicationEntity.getJobOffer().getMustHaveSkills(),
                jobApplicationEntity.getJobOffer().getNiceToHaveSkills()
        );

        // Mapowanie EmployerEntity -> Employer
        Employer employer = mapEmployer(jobApplicationEntity.getEmployer());

        // Mapowanie CandidateEntity -> Candidate
        Candidate candidate = mapCandidate(jobApplicationEntity.getCandidate());

        // Tworzymy nowy obiekt JobApplication przy użyciu konstruktora
        return new JobApplication(
                jobApplicationEntity.getApplicationId(),
                jobOffer, // Dodajemy mapowany JobOffer
                employer, // Dodajemy mapowany Employer
                candidate, // Dodajemy mapowanego kandydata
                jobApplicationEntity.getApplicationStatus() // Status aplikacji
        );
    }



    // Ręczne mapowanie EmployerEntity -> Employer
    private Employer mapEmployer(EmployerEntity employerEntity) {
        return new Employer(
                employerEntity.getEmployerId(),
                employerEntity.getCompanyName(),
                employerEntity.getEmail(),
                employerEntity.getNip(),
                employerEntity.getUserId()
        );
    }

    // Ręczne mapowanie Employer -> EmployerEntity
    private EmployerEntity mapEmployerToEntity(Employer employer) {
        return new EmployerEntity(
                employer.getEmployerId(),
                employer.getCompanyName(),
                employer.getEmail(),
                employer.getNip(),
                employer.getUserId(),
                null, // JobOffers, JobApplications mapping can be done separately if needed
                null  // Additional fields can be handled as required
        );
    }

    private Candidate mapCandidate(CandidateEntity candidateEntity) {
        return new Candidate.Builder()
                .candidateId(candidateEntity.getCandidateId())
                .name(candidateEntity.getName())
                .surname(candidateEntity.getSurname())
                .email(candidateEntity.getEmail())
                .phone(candidateEntity.getPhone())
                .techSpecialization(candidateEntity.getTechSpecialization())
                .candidateSkills(candidateEntity.getCandidateSkills())
                .build();
    }


    // Ręczne mapowanie Candidate -> CandidateEntity
    private CandidateEntity mapCandidateToEntity(Candidate candidate) {
        return new CandidateEntity(
                candidate.getCandidateId(),
                candidate.getName(),
                candidate.getSurname(),
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getTechSpecialization(),
                candidate.getCandidateSkills()
        );
    }
}
