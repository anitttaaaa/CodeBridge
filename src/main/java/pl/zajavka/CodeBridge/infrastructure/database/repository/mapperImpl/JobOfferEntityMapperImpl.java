package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobOfferEntityMapper;

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
                .salary(jobOfferEntity.getSalary())
                .employer(mapEmployerToDomain(jobOfferEntity.getEmployer()))
                .mustHaveSkills(jobOfferEntity.getMustHaveSkills())
                .niceToHaveSkills(jobOfferEntity.getNiceToHaveSkills())
                .build();
    }

    private Employer mapEmployerToDomain(EmployerEntity employerEntity) {
        return new Employer.EmployerBuilder()
                .employerId(employerEntity.getEmployerId())
                .companyName(employerEntity.getCompanyName())
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
                .salary(jobOffer.getSalary())
                .employer(mapEmployerToEntity(jobOffer.getEmployer()))
                .mustHaveSkills(jobOffer.getMustHaveSkills())
                .niceToHaveSkills(jobOffer.getNiceToHaveSkills())
                .build();
    }

    public EmployerEntity mapEmployerToEntity(Employer employer) {
        return new EmployerEntity.Builder()
                .employerId(employer.getEmployerId())
                .build();
    }
}
