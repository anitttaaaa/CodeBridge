package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobApplicationEntityMapper;

@Component
public class JobApplicationEntityMapperImpl implements JobApplicationEntityMapper {

    @Override
    public JobApplicationEntity mapToEntity(JobApplication jobApplication) {
        JobOfferEntity jobOfferEntity = new JobOfferEntity.Builder()
                .jobOfferId(jobApplication.getJobOffer().getJobOfferId())
                .jobOfferTitle(jobApplication.getJobOffer().getJobOfferTitle())
                .description(jobApplication.getJobOffer().getDescription())
                .mustHaveSkills(jobApplication.getJobOffer().getMustHaveSkills())
                .niceToHaveSkills(jobApplication.getJobOffer().getNiceToHaveSkills())
                .build();

        EmployerEntity employerEntity = mapEmployerToEntity(jobApplication.getEmployer());

        CandidateEntity candidateEntity = mapCandidateToEntity(jobApplication.getCandidate());

        return new JobApplicationEntity.Builder()
                .applicationId(jobApplication.getApplicationId())
                .jobOffer(jobOfferEntity)
                .employer(employerEntity)
                .candidate(candidateEntity)
                .applicationStatus(jobApplication.getApplicationStatus())
                .build();
    }

    @Override
    public JobApplication mapToDomain(JobApplicationEntity jobApplicationEntity) {
        JobOffer jobOffer = new JobOffer.JobOfferBuilder()
                .jobOfferId(jobApplicationEntity.getJobOffer().getJobOfferId())
                .jobOfferTitle(jobApplicationEntity.getJobOffer().getJobOfferTitle())
                .description(jobApplicationEntity.getJobOffer().getDescription())
                .techSpecialization(jobApplicationEntity.getJobOffer().getTechSpecialization().name())
                .employer(mapEmployer(jobApplicationEntity.getJobOffer().getEmployer())) // Employer mapowany z JobOffer
                .workType(jobApplicationEntity.getJobOffer().getWorkType().name())
                .city(jobApplicationEntity.getJobOffer().getCity().name())
                .experience(jobApplicationEntity.getJobOffer().getExperience().name())
                .salary(jobApplicationEntity.getJobOffer().getSalary().name())
                .mustHaveSkills(jobApplicationEntity.getJobOffer().getMustHaveSkills())
                .niceToHaveSkills(jobApplicationEntity.getJobOffer().getNiceToHaveSkills())
                .build();

        Employer employer = mapEmployer(jobApplicationEntity.getEmployer());

        Candidate candidate = mapCandidate(jobApplicationEntity.getCandidate());

        return new JobApplication.JobApplicationBuilder()
                .applicationId(jobApplicationEntity.getApplicationId())
                .jobOffer(jobOffer)
                .employer(employer)
                .candidate(candidate)
                .jobApplicationStatus(jobApplicationEntity.getApplicationStatus())
                .build();
    }

    private Employer mapEmployer(EmployerEntity employerEntity) {
        return new Employer.EmployerBuilder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
                .email(employerEntity.getEmail())
                .nip(employerEntity.getNip())
                .userId(employerEntity.getUserId())
                .build();
    }

    private EmployerEntity mapEmployerToEntity(Employer employer) {
        return new EmployerEntity(
                employer.getEmployerId(),
                employer.getCompanyName(),
                employer.getEmail(),
                employer.getNip(),
                employer.getUserId(),
                null,
                null
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

    private CandidateEntity mapCandidateToEntity(Candidate candidate) {
        return new CandidateEntity.Builder()
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
