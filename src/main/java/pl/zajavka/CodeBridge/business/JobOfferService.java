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

        JobOffer jobOffer = new JobOffer.JobOfferBuilder()
                .jobOfferTitle(request.getJobOfferTitle())
                .description(request.getDescription())
                .techSpecialization(request.getTechSpecialization())
                .workType(request.getWorkType())
                .city(request.getCity())
                .experience(request.getExperience())
                .salary(request.getSalary())
                .mustHaveSkills(request.getMustHaveSkills())
                .niceToHaveSkills(request.getNiceToHaveSkills())
                .employer(employer)
                .build();

        System.out.println(jobOffer + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");

        Set<JobOffer> jobOffers = new HashSet<>(employer.getJobOffers());
        jobOffers.add(jobOffer);

        Employer employerAndJobOffer = new Employer.EmployerBuilder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .email(employer.getEmail())
                .nip(employer.getNip())
                .userId(employer.getUserId())
                .jobOffers(jobOffers)
                .jobApplications(employer.getJobApplications())
                .build();

        employerService.createJobOffer(employerAndJobOffer);
    }


    private JobOffer buildJobOffer(JobOffer request) {
        return new JobOffer.JobOfferBuilder()
                .jobOfferId(null)
                .jobOfferTitle(request.getJobOfferTitle())
                .description(request.getDescription())
                .techSpecialization(request.getTechSpecialization())
                .employer(request.getEmployer())
                .workType(request.getWorkType())
                .city(request.getCity())
                .experience(request.getExperience())
                .salary(request.getSalary())
                .mustHaveSkills(request.getMustHaveSkills())
                .niceToHaveSkills(request.getNiceToHaveSkills())
                .build();
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
