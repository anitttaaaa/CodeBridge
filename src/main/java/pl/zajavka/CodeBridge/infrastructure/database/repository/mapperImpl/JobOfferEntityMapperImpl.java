package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.enums.*;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobOfferEntityMapper;

@Component
public class JobOfferEntityMapperImpl implements JobOfferEntityMapper {

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

    @Override
    public JobOffer mapToDomain(JobOfferEntity jobOfferEntity) {
        String techSpecialization = jobOfferEntity.getTechSpecialization() != null
                ? jobOfferEntity.getTechSpecialization().name()
                : null;
        String workType = jobOfferEntity.getWorkType() != null
                ? jobOfferEntity.getWorkType().name()
                : null;
        String city = jobOfferEntity.getCity() != null
                ? jobOfferEntity.getCity().name()
                : null;
        String experience = jobOfferEntity.getExperience() != null
                ? jobOfferEntity.getExperience().name()
                : null;
        String salary = jobOfferEntity.getSalary() != null
                ? jobOfferEntity.getSalary().name()
                : null;

        Employer employer = jobOfferEntity.getEmployer() != null
                ? new Employer.EmployerBuilder()
                .employerId(jobOfferEntity.getEmployer().getEmployerId())
                .companyName(jobOfferEntity.getEmployer().getCompanyName())
                .email(null)
                .nip(null)
                .userId(null)
                .build()
                : null;

        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferEntity.getJobOfferId())
                .jobOfferTitle(jobOfferEntity.getJobOfferTitle())
                .description(jobOfferEntity.getDescription())
                .techSpecialization(techSpecialization)
                .employer(employer)
                .workType(workType)
                .city(city)
                .experience(experience)
                .salary(salary)
                .mustHaveSkills(jobOfferEntity.getMustHaveSkills())
                .niceToHaveSkills(jobOfferEntity.getNiceToHaveSkills())
                .build();
    }

    @Override
    public JobOfferEntity mapToEntity(JobOffer jobOffer) {
        // Mapowanie prostych pól
        TechSpecializationsEnum techSpecialization = jobOffer.getTechSpecialization() != null
                ? TechSpecializationsEnum.valueOf(jobOffer.getTechSpecialization())
                : null;
        WorkTypesEnum workType = jobOffer.getWorkType() != null
                ? WorkTypesEnum.valueOf(jobOffer.getWorkType())
                : null;
        CitiesEnum city = jobOffer.getCity() != null
                ? CitiesEnum.valueOf(jobOffer.getCity())
                : null;
        ExperiencesEnum experience = jobOffer.getExperience() != null
                ? ExperiencesEnum.valueOf(jobOffer.getExperience())
                : null;
        SalaryRangeEnum salary = jobOffer.getSalary() != null
                ? SalaryRangeEnum.valueOf(jobOffer.getSalary())
                : null;

        // Mapowanie obiektu Employer
        EmployerEntity employerEntity = jobOffer.getEmployer() != null
                ? new EmployerEntity(jobOffer.getEmployer().getEmployerId(), jobOffer.getEmployer().getCompanyName(),
                null, null, null, null, null) // Możesz dodać dodatkowe pola, jeśli potrzebujesz
                : null;

        return new JobOfferEntity.Builder()
                .jobOfferId(jobOffer.getJobOfferId())
                .jobOfferTitle(jobOffer.getJobOfferTitle())
                .description(jobOffer.getDescription())
                .techSpecialization(techSpecialization)
                .workType(workType)
                .city(city)
                .experience(experience)
                .salary(salary)
                .employer(employerEntity)
                .jobApplications(null) // Możesz dodać mapowanie dla jobApplications, jeśli to konieczne
                .mustHaveSkills(jobOffer.getMustHaveSkills())
                .niceToHaveSkills(jobOffer.getNiceToHaveSkills())
                .build();
    }
}
