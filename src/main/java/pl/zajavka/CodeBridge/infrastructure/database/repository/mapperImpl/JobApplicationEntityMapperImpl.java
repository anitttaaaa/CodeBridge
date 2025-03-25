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

        return new JobApplicationEntity.Builder()
                .applicationId(jobApplication.getApplicationId())
                .applicationStatus(jobApplication.getApplicationStatusEnum())
                .jobOffer(mapJobOfferToEntity(jobApplication.getJobOffer()))
                .candidate(mapCandidateToEntity(jobApplication.getCandidate()))
                .employer(mapEmployerToEntity(jobApplication.getEmployer()))
                .build();
    }

    private EmployerEntity mapEmployerToEntity(Employer employer) {
        return new EmployerEntity.Builder()
                .employerId(employer.getEmployerId())
                .build();
    }

    private CandidateEntity mapCandidateToEntity(Candidate candidate) {
        return new CandidateEntity.Builder()
                .candidateId(candidate.getCandidateId())
                .build();
    }

    private JobOfferEntity mapJobOfferToEntity(JobOffer jobOffer) {
        return new JobOfferEntity.Builder()
                .jobOfferId(jobOffer.getJobOfferId())
                .build();
    }


    @Override
    public JobApplication mapToDomain(JobApplicationEntity jobApplicationEntity) {

        return new JobApplication.JobApplicationBuilder()
                .applicationId(jobApplicationEntity.getApplicationId())
                .jobOffer(mapJobOfferToDomain(jobApplicationEntity.getJobOffer()))
                .employer(mapEmployerToDomain(jobApplicationEntity.getEmployer()))
                .candidate(mapCandidateToDomain(jobApplicationEntity.getCandidate()))
                .jobApplicationStatus(jobApplicationEntity.getApplicationStatus())
                .build();
    }

    private Candidate mapCandidateToDomain(CandidateEntity candidate) {
        return new Candidate.Builder()
                .candidateId(candidate.getCandidateId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .techSpecialization(candidate.getTechSpecialization())
                .candidateSkills(candidate.getCandidateSkills())
                .build();
    }

    private Employer mapEmployerToDomain(EmployerEntity employer) {
        return new Employer.EmployerBuilder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .build();
    }

    private JobOffer mapJobOfferToDomain(JobOfferEntity jobOffer) {
        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOffer.getJobOfferId())
                .jobOfferTitle(jobOffer.getJobOfferTitle())
                .description(jobOffer.getDescription())
                .techSpecialization(jobOffer.getTechSpecialization())
                .experience(jobOffer.getExperience())
                .workType(jobOffer.getWorkType())
                .city(jobOffer.getCity())
                .salary(jobOffer.getSalary())
                .mustHaveSkills(jobOffer.getMustHaveSkills())
                .niceToHaveSkills(jobOffer.getNiceToHaveSkills())
                .build();
    }


}
