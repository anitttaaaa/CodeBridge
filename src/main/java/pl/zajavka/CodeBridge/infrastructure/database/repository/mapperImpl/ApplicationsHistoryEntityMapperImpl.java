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

    @Override
    public JobOffer mapJobOffer(JobOfferEntity jobOfferEntity) {
        Employer employer = new Employer.EmployerBuilder()
                .employerId(jobOfferEntity.getEmployer().getEmployerId())
                .companyName(jobOfferEntity.getEmployer().getCompanyName())
                .email(jobOfferEntity.getEmployer().getEmail())
                .nip(jobOfferEntity.getEmployer().getNip())
                .userId(jobOfferEntity.getEmployer().getUserId())
                .build();

        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferEntity.getJobOfferId())
                .jobOfferTitle(jobOfferEntity.getJobOfferTitle())
                .description(jobOfferEntity.getDescription())
                .techSpecialization(TechSpecializationsEnum.valueOf(jobOfferEntity.getTechSpecialization().name()))
                .employer(employer)
                .workType(jobOfferEntity.getWorkType())
                .city(jobOfferEntity.getCity())
                .experience(jobOfferEntity.getExperience())
                .salary(jobOfferEntity.getSalary())
                .mustHaveSkills(jobOfferEntity.getMustHaveSkills())
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
                .applicationStatus(applicationsHistory.getApplicationStatusEnum())
                .build();
    }


    @Override
    public JobOfferEntity mapJobOfferEntity(JobOffer jobOffer) {
        EmployerEntity employerEntity = new EmployerEntity(
                jobOffer.getEmployer().getEmployerId(),
                jobOffer.getEmployer().getCompanyName(),
                jobOffer.getEmployer().getEmail(),
                jobOffer.getEmployer().getNip(),
                jobOffer.getEmployer().getUserId()
        );

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
