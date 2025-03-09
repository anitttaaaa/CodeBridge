package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.JobOfferMapper;
import pl.zajavka.CodeBridge.domain.JobOffer;

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
                .salary(jobOfferDTO.getSalary())
                .mustHaveSkills(jobOfferDTO.getMustHaveSkills())
                .niceToHaveSkills(jobOfferDTO.getNiceToHaveSkills())
                .employer(jobOfferDTO.getEmployer())
                .build();
    }

    @Override
    public JobOfferDTO mapToDTO(JobOffer jobOffer) {
        return new JobOfferDTO.JobOfferDTOBuilder()
                .jobOfferId(jobOffer.getJobOfferId())
                .jobOfferTitle(jobOffer.getJobOfferTitle())
                .description(jobOffer.getDescription())
                .techSpecialization(jobOffer.getTechSpecialization())
                .workType(jobOffer.getWorkType())
                .city(jobOffer.getCity())
                .experience(jobOffer.getExperience())
                .salary(jobOffer.getSalary())
                .mustHaveSkills(jobOffer.getMustHaveSkills())
                .niceToHaveSkills(jobOffer.getNiceToHaveSkills())
                .employer(jobOffer.getEmployer())
                .build();
    }
}
