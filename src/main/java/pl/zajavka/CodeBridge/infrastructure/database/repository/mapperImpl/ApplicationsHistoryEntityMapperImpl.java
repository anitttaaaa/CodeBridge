package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;
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

    public JobOffer mapJobOffer(JobOfferEntity jobOfferEntity) {

        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferEntity.getJobOfferId())
                .jobOfferTitle(jobOfferEntity.getJobOfferTitle())
                .description(jobOfferEntity.getDescription())
                .techSpecialization(TechSpecializationsEnum.valueOf(jobOfferEntity.getTechSpecialization().name()))
                .workType(jobOfferEntity.getWorkType())
                .city(jobOfferEntity.getCity())
                .experience(jobOfferEntity.getExperience())
                .salary(jobOfferEntity.getSalary())
                .mustHaveSkills(jobOfferEntity.getMustHaveSkills())
                .niceToHaveSkills(jobOfferEntity.getNiceToHaveSkills())
                .build();
    }

    public Employer mapEmployer(EmployerEntity employerEntity) {
        return new Employer.EmployerBuilder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
                .build();
    }

    public Candidate mapCandidate(CandidateEntity candidateEntity) {
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

    public ApplicationsHistoryEntity mapToEntity(ApplicationsHistory applicationsHistory) {
        return new ApplicationsHistoryEntity.Builder()
                .applicationHistoryId(applicationsHistory.getApplicationHistoryId())
                .jobOffer(mapJobOfferEntity(applicationsHistory.getJobOffer()))
                .employer(mapEmployerEntity(applicationsHistory.getEmployer()))
                .candidate(mapCandidateEntity(applicationsHistory.getCandidate()))
                .applicationStatus(applicationsHistory.getApplicationStatusEnum())
                .build();
    }


    public JobOfferEntity mapJobOfferEntity(JobOffer jobOffer) {
        return new JobOfferEntity.Builder()
                .jobOfferId(jobOffer.getJobOfferId())
                .build();
    }


    public EmployerEntity mapEmployerEntity(Employer employer) {
        return new EmployerEntity.Builder()
                .employerId(employer.getEmployerId())
                .build();

    }

    public CandidateEntity mapCandidateEntity(Candidate candidate) {
        return new CandidateEntity.Builder()
                .candidateId(candidate.getCandidateId())
                .build();

    }
}
