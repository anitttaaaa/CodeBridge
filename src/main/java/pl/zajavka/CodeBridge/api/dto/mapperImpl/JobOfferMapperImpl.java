package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.JobOfferMapper;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.api.enums.SalaryEnum;

@Component
public class JobOfferMapperImpl implements JobOfferMapper {

    @Override
    public JobOffer mapToDomain(JobOfferDTO jobOfferDTO) {
        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferDTO.getJobOfferId())
                .jobOfferTitle(jobOfferDTO.getJobOfferTitle())
                .description(jobOfferDTO.getDescription())
                .techSpecialization(jobOfferDTO.getTechSpecialization())
                .workType(jobOfferDTO.getWorkType())
                .city(jobOfferDTO.getCity())
                .experience(jobOfferDTO.getExperience())
                // Użycie SalaryEnum podczas mapowania
                .salary(jobOfferDTO.getSalary())// Zakładając, że w DTO jest String, który reprezentuje wartość enumu
                .mustHaveSkills(jobOfferDTO.getMustHaveSkills())
                .niceToHaveSkills(jobOfferDTO.getNiceToHaveSkills())
                .employer(jobOfferDTO.getEmployer())
                .build();
    }

    @Override
    public JobOfferDTO mapToDTO(JobOffer jobOffer) {
        return new JobOfferDTO(
                jobOffer.getJobOfferId(),
                jobOffer.getJobOfferTitle(),
                jobOffer.getDescription(),
                jobOffer.getTechSpecialization(),
                jobOffer.getWorkType(),
                jobOffer.getCity(),
                jobOffer.getExperience(),
                // Przekładanie SalaryEnum na String w DTO
                SalaryEnum.valueOf(jobOffer.getSalary().name()), // Zamieniamy SalaryEnum na String przy mapowaniu do DTO
                jobOffer.getMustHaveSkills(),
                jobOffer.getNiceToHaveSkills(),
                jobOffer.getEmployer()
                );
    }
}
