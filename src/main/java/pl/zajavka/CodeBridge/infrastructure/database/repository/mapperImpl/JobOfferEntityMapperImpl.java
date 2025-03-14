package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.enums.*;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobOfferEntityMapper;

import java.util.HashSet;

@Component
public class JobOfferEntityMapperImpl implements JobOfferEntityMapper {

    @Override
    public JobOffer mapToDomain(JobOfferEntity jobOfferEntity) {
        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferEntity.getJobOfferId())
                .jobOfferTitle(jobOfferEntity.getJobOfferTitle())
                .description(jobOfferEntity.getDescription())
                .techSpecialization(jobOfferEntity.getTechSpecialization())
                .workType(jobOfferEntity.getWorkType())
                .city(jobOfferEntity.getCity())
                .experience(jobOfferEntity.getExperience())
                // Użycie SalaryEnum, upewniając się, że jest to odpowiednia wartość z enumu
                .salary(jobOfferEntity.getSalary())// Zakładając, że w encji jest przechowywana wartość w postaci String
                .employer(mapEmployerToDomain(jobOfferEntity.getEmployer()))
                .mustHaveSkills(jobOfferEntity.getMustHaveSkills())
                .niceToHaveSkills(jobOfferEntity.getNiceToHaveSkills())
                .build();
    }

    private Employer mapEmployerToDomain(EmployerEntity employerEntity) {
        return new Employer.EmployerBuilder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
                .email(employerEntity.getEmail())
                .nip(employerEntity.getNip())
                .userId(employerEntity.getUserId())
                .build();
    }

    @Override
    public JobOfferEntity mapToEntity(JobOffer jobOffer) {
        return new JobOfferEntity.Builder()
                .jobOfferId(jobOffer.getJobOfferId())
                .jobOfferTitle(jobOffer.getJobOfferTitle())
                .description(jobOffer.getDescription())
                .techSpecialization(jobOffer.getTechSpecialization())
                .workType(jobOffer.getWorkType())
                .city(jobOffer.getCity())
                .experience(jobOffer.getExperience())
                // Użycie SalaryEnum, upewniając się, że jest to odpowiednia wartość z enumu
                .salary(jobOffer.getSalary())// Zakładając, że w encji jest przechowywana wartość w postaci String
                .employer(mapEmployerToEntity(jobOffer.getEmployer()))
                .mustHaveSkills(jobOffer.getMustHaveSkills())
                .niceToHaveSkills(jobOffer.getNiceToHaveSkills())
                .build();
    }

    @Override
    public EmployerEntity mapEmployerToEntity(Employer employer) {
        return new EmployerEntity.Builder()
                .employerId(employer.getEmployerId())
                .build();
    }
}
