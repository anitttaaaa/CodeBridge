package pl.zajavka.CodeBridge.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.JobOfferMapper;
import pl.zajavka.CodeBridge.api.enums.*;
import pl.zajavka.CodeBridge.business.dao.JobOfferDAO;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobOfferService {

    private final CodeBridgeUserDetailsService codeBridgeUserDetailsService;
    private final EmployerService employerService;
    private final JobOfferDAO jobOfferDAO;
    private final JobOfferMapper jobOfferMapper;

    @Autowired
    public JobOfferService(CodeBridgeUserDetailsService codeBridgeUserDetailsService,
                           EmployerService employerService,
                           JobOfferDAO jobOfferDAO,
                           JobOfferMapper jobOfferMapper) {
        this.codeBridgeUserDetailsService = codeBridgeUserDetailsService;
        this.employerService = employerService;
        this.jobOfferDAO = jobOfferDAO;
        this.jobOfferMapper = jobOfferMapper;
    }

    @Transactional
    public void createJobOfferData(
            String jobOfferTitle,
            String description,
            String techSpecialization,
            String workType,
            String city,
            String experience,
            String salary,
            List<SkillsEnum> mustHaveSkills,
            List<SkillsEnum> niceToHaveSkills,
            Authentication authentication) {

        Employer employer = employerService.findEmployerByEmail(authentication.getName());

        JobOfferDTO jobOfferDTO = new JobOfferDTO.Builder()
                .jobOfferTitle(jobOfferTitle)
                .description(description)
                .techSpecialization(TechSpecializationsEnum.valueOf(techSpecialization))
                .workType(WorkTypesEnum.valueOf(workType))
                .city(CitiesEnum.valueOf(city))
                .experience(ExperiencesEnum.valueOf(experience))
                .salary(SalaryEnum.valueOf(salary))
                .mustHaveSkills(mustHaveSkills)
                .niceToHaveSkills(niceToHaveSkills)
                .employer(employer)
                .build();

        JobOffer jobOffer = jobOfferMapper.mapToDomain(jobOfferDTO);

        employerService.createJobOffer(jobOffer);
    }


    @Transactional
    public List<JobOfferDTO> getAllJobOffersSorted() {

        List<JobOffer> jobOffers = jobOfferDAO.findAllJobOffers();
        List<JobOfferDTO> jobOffersDTO = jobOffers.stream()
                .map(jobOfferMapper::mapToDTO)
                .collect(Collectors.toList());

        return jobOffersDTO.stream()
                .sorted(Comparator.comparingInt(JobOfferDTO::getJobOfferId).reversed())
                .collect(Collectors.toList());
    }


    @Transactional
    public List<JobOfferDTO> getFilteredAndSortedJobOffers(
            TechSpecializationsEnum techSpecialization,
            WorkTypesEnum workType,
            CitiesEnum city,
            ExperiencesEnum experience,
            SalaryEnum salary) {

        List<JobOffer> filteredJobOffers = jobOfferDAO.findAll().stream()
                .filter(job -> techSpecialization == null || techSpecialization.equals(job.getTechSpecialization()))
                .filter(job -> workType == null || workType.equals(job.getWorkType()))
                .filter(job -> city == null || city.equals(job.getCity()))
                .filter(job -> experience == null || experience.equals(job.getExperience()))
                .filter(job -> salary == null || job.getSalary().equals(salary))
                .sorted(Comparator.comparingInt(JobOffer::getJobOfferId).reversed())
                .collect(Collectors.toList());

        return filteredJobOffers.stream()
                .map(jobOfferMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public JobOffer findJobOffer(Integer jobOfferId) {
        Optional<JobOffer> jobOffer = jobOfferDAO.findById(jobOfferId);
        if (jobOffer.isEmpty()) {
            throw new NotFoundException("Could not find employer by user id: [%s]".formatted(jobOfferId));
        }
        return jobOffer.get();
    }


    @Transactional
    public List<JobOfferDTO> getSortedJobOffersByEmployerId(Authentication authentication) {

        Employer employer = employerService.findEmployerByEmail(authentication.getName());
        Integer employerId = employer.getEmployerId();

        List<JobOffer> jobOffers = jobOfferDAO.findJobOffersByEmployerId(employerId);

        return jobOffers.stream()
                .map(jobOfferMapper::mapToDTO)
                .sorted(Comparator.comparingInt(JobOfferDTO::getJobOfferId).reversed())
                .collect(Collectors.toList());
    }

    public JobOfferDTO createNewJobOfferDTO() {
        return new JobOfferDTO.Builder()
                .jobOfferId(null)
                .jobOfferTitle("")
                .description("")
                .techSpecialization(TechSpecializationsEnum.BACKEND)
                .workType(WorkTypesEnum.REMOTE)
                .city(CitiesEnum.WARSZAWA)
                .experience(ExperiencesEnum.BEGINNER)
                .salary(SalaryEnum.PLN_0_3000)
                .mustHaveSkills(List.of())
                .niceToHaveSkills(List.of())
                .employer(null)
                .build();
    }
}
