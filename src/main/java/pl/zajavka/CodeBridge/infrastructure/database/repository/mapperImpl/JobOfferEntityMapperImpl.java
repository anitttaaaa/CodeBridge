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
                .techSpecialization(jobOfferEntity.getTechSpecialization().name())
                .workType(jobOfferEntity.getWorkType().name())
                .city(jobOfferEntity.getCity().name())
                .experience(jobOfferEntity.getExperience().name())
                .salary(jobOfferEntity.getSalary().name())
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
        return JobOfferEntity.builder()
                .jobOfferId(jobOffer.getJobOfferId())
                .jobOfferTitle(jobOffer.getJobOfferTitle())
                .description(jobOffer.getDescription())
                .techSpecialization(TechSpecializationsEnum.valueOf(jobOffer.getTechSpecialization()))
                .workType(WorkTypesEnum.valueOf(jobOffer.getWorkType()))
                .city(CitiesEnum.valueOf(jobOffer.getCity()))
                .experience(ExperiencesEnum.valueOf(jobOffer.getExperience()))
                .salary(SalaryRangeEnum.valueOf(jobOffer.getSalary()))
                .employer(mapEmployerToEntity(jobOffer.getEmployer()))
                .jobApplications(new HashSet<>()) // Możesz ustawić pusty set zamiast `null`
                .mustHaveSkills(jobOffer.getMustHaveSkills())
                .niceToHaveSkills(jobOffer.getNiceToHaveSkills())
                .build();
    }

    private EmployerEntity mapEmployerToEntity(Employer employer) {
        return EmployerEntity.builder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .email(employer.getEmail())
                .nip(employer.getNip())
                .userId(employer.getUserId())
                .jobOffers(new HashSet<>()) // Pusty set zamiast `null`
                .jobApplications(new HashSet<>()) // Pusty set zamiast `null`
                .build();
    }

    @Override
    public Employer mapEmployer(String companyName, Integer employerId) {
        if (companyName == null || employerId == null) {
            return null;
        }
        return new Employer.EmployerBuilder()
                .employerId(employerId)
                .companyName(companyName)
                .build();
    }
}
