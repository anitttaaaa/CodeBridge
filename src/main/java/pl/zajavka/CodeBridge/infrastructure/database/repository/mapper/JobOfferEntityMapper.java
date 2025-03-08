package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.api.enums.*;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobOfferEntityMapper {

    default Employer mapEmployer(String companyName, Integer employerId) {
        if (companyName == null || employerId == null) {
            return null;
        }
        return Employer.builder()
                .companyName(companyName)
                .employerId(employerId)
                .build();
    }
    default JobOffer mapToDomain(JobOfferEntity jobOfferEntity) {
        // Mapowanie prostych pól
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

        // Mapowanie obiektu Employer
        Employer employer = jobOfferEntity.getEmployer() != null
                ? new Employer(jobOfferEntity.getEmployer().getEmployerId(),
                jobOfferEntity.getEmployer().getCompanyName(), null, null, null, null, null) // Dostosuj do potrzebnych danych
                : null;

        // Tworzenie JobOffer
        return new JobOffer(
                jobOfferEntity.getJobOfferId(),
                jobOfferEntity.getJobOfferTitle(),
                jobOfferEntity.getDescription(),
                techSpecialization,
                employer,
                workType,
                city,
                experience,
                salary,
                jobOfferEntity.getMustHaveSkills(),
                jobOfferEntity.getNiceToHaveSkills()
        );
    }

    default JobOfferEntity mapToEntity(JobOffer jobOffer) {
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

        // Tworzenie JobOfferEntity
        return new JobOfferEntity(
                jobOffer.getJobOfferId(),
                jobOffer.getJobOfferTitle(),
                jobOffer.getDescription(),
                techSpecialization,
                workType,
                city,
                experience,
                salary,
                employerEntity,
                null, // Możesz dodać mapowanie dla jobApplications, jeśli to konieczne
                jobOffer.getMustHaveSkills(),
                jobOffer.getNiceToHaveSkills()
        );
    }



}
