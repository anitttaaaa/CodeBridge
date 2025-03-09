package pl.zajavka.CodeBridge.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.JobOfferMapper;
import pl.zajavka.CodeBridge.business.dao.JobOfferDAO;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public void createJobOfferData(JobOffer request, Authentication authentication) {
        String username = authentication.getName();
        Integer userId = codeBridgeUserDetailsService.getUserId(username);
        Employer employer = employerService.findEmployer(userId);

        // Create the JobOffer using the Builder pattern
        JobOffer jobOffer = new JobOffer.JobOfferBuilder()
                .jobOfferTitle(request.getJobOfferTitle())  // Example fields, update with actual ones
                .description(request.getDescription()) // Example fields, update with actual ones
                .salary(request.getSalary()) // Example fields, update with actual ones
                .build();

        // Create a new Set of job offers and add the new job offer to it
        Set<JobOffer> jobOffers = new HashSet<>(employer.getJobOffers());
        jobOffers.add(jobOffer);

        // Create the Employer object using the Builder pattern
        Employer employerAndJobOffer = new Employer.EmployerBuilder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .email(employer.getEmail())
                .nip(employer.getNip())
                .userId(employer.getUserId())
                .jobOffers(jobOffers)
                .jobApplications(employer.getJobApplications())
                .build();

        // Save the updated Employer object with the new job offer
        employerService.createJobOffer(employerAndJobOffer);
    }


    private JobOffer buildJobOffer(JobOffer request) {
        return new JobOffer.JobOfferBuilder()
                .jobOfferId(null)  // Job offer ID will be null since it’s a new job offer
                .jobOfferTitle(request.getJobOfferTitle())
                .description(request.getDescription())
                .techSpecialization(request.getTechSpecialization())
                .employer(request.getEmployer())  // Pass Employer if part of the object
                .workType(request.getWorkType())
                .city(request.getCity())
                .experience(request.getExperience())
                .salary(request.getSalary())
                .mustHaveSkills(request.getMustHaveSkills())
                .niceToHaveSkills(request.getNiceToHaveSkills())
                .build();  // Return the built JobOffer object
    }


    @Transactional
    public List<JobOffer> getAllJobOffers() {

        return jobOfferDAO.findAllJobOffers();
    }


    public List<JobOffer> getFilteredJobOffers(
            String techSpecialization,
            String workType,
            String city,
            String experience,
            String salary) {

        List<JobOffer> allJobOffers = jobOfferDAO.findAll();

        return allJobOffers.stream()
                .filter(job -> techSpecialization == null || techSpecialization.equals(job.getTechSpecialization()))
                .filter(job -> workType == null || workType.equals(job.getWorkType()))
                .filter(job -> city == null || city.equals(job.getCity()))
                .filter(job -> experience == null || experience.equals(job.getExperience()))
                .filter(job -> salary == null || job.getSalary().equals(salary))
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


    public List<JobOfferDTO> getJobOffersByEmployerId(Authentication authentication) {

        String employerEmail = authentication.getName();
        Integer employerId = employerService.findEmployerByEmail(employerEmail).getEmployerId();

        List<JobOffer> employerJobOffers = jobOfferDAO.findJobOffersByEmployerId(employerId);

        return employerJobOffers.stream()
                .map(jobOfferMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
