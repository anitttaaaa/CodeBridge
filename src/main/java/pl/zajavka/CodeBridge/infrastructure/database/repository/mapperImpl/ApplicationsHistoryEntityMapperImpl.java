package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.ApplicationsHistoryEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.ApplicationsHistoryEntityMapper;

@Component
public class ApplicationsHistoryEntityMapperImpl implements ApplicationsHistoryEntityMapper {

    @Override
    public ApplicationsHistory mapToDomain(ApplicationsHistoryEntity entity) {
        return new ApplicationsHistory.Builder()
                .applicationHistoryId(entity.getApplicationHistoryId())
                .jobOffer(mapJobOffer(entity.getJobOffer()))
                .employer(mapEmployer(entity.getEmployer()))
                .candidate(mapCandidate(entity.getCandidate()))
                .applicationStatus(entity.getApplicationStatus())
                .build();
    }

    @Override
    public JobOffer mapJobOffer(JobOfferEntity jobOfferEntity) {
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

    @Override
    public Employer mapEmployer(EmployerEntity employerEntity) {
        return new Employer.EmployerBuilder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
                .email(employerEntity.getEmail())
                .nip(employerEntity.getNip())
                .userId(employerEntity.getUserId())
                .build();
    }

    @Override
    public Candidate mapCandidate(CandidateEntity candidateEntity) {
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

    @Override
    public ApplicationsHistoryEntity mapToEntity(ApplicationsHistory applicationsHistory) {
        return new ApplicationsHistoryEntity.Builder()
                .applicationHistoryId(applicationsHistory.getApplicationHistoryId())
                .jobOffer(mapJobOfferEntity(applicationsHistory.getJobOffer()))
                .employer(mapEmployerEntity(applicationsHistory.getEmployer()))
                .candidate(mapCandidateEntity(applicationsHistory.getCandidate()))
                .applicationStatus(applicationsHistory.getApplicationStatus())
                .build();
    }


    @Override
    public JobOfferEntity mapJobOfferEntity(JobOffer jobOffer) {
        EmployerEntity employerEntity = jobOffer.getEmployer() != null
                ? new EmployerEntity(
                jobOffer.getEmployer().getEmployerId(),
                jobOffer.getEmployer().getCompanyName(),
                jobOffer.getEmployer().getEmail(),
                jobOffer.getEmployer().getNip(),
                jobOffer.getEmployer().getUserId()
        )
                : null;

        return new JobOfferEntity.Builder()
                .jobOfferId(jobOffer.getJobOfferId())
                .jobOfferTitle(jobOffer.getJobOfferTitle())
                .description(jobOffer.getDescription())
                .employer(employerEntity)
                .build();
    }

    @Override
    public EmployerEntity mapEmployerEntity(Employer employer) {
        return new EmployerEntity(
                employer.getEmployerId(),
                employer.getCompanyName(),
                employer.getEmail(),
                employer.getNip(),
                employer.getUserId()
        );
    }

    @Override
    public CandidateEntity mapCandidateEntity(Candidate candidate) {
        // Użycie Buildera
        return new CandidateEntity.Builder()
                .candidateId(candidate.getCandidateId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .userId(candidate.getUserId())
                .techSpecialization(candidate.getTechSpecialization())
                .candidateSkills(candidate.getCandidateSkills())
                .build();

    }
}
