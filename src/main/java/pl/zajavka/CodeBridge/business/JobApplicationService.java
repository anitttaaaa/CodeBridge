package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.JobApplicationMapper;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;
import pl.zajavka.CodeBridge.business.dao.JobApplicationDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobApplicationService {

    private final CandidateService candidateService;
    private final JobApplicationDAO jobApplicationDAO;
    private final JobOfferService jobOfferService;
    private final JobApplicationMapper jobApplicationMapper;
    private final EmployerService employerService;

    @Transactional
    public void applyForJob(Integer jobOfferId, Authentication authentication) {

        String candidateEmail = authentication.getName();
        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();
        Integer employerId = getEmployerId(jobOfferId);

        ApplicationStatus applicationStatus = ApplicationStatus.PENDING;

        JobApplication jobApplication = JobApplication.builder()
                .jobOffer(JobOffer.builder().jobOfferId(jobOfferId).build())
                .employer(Employer.builder().employerId(employerId).build())
                .candidate(Candidate.builder().candidateId(candidateId).build())
                .applicationStatus(applicationStatus)
                .build();

        jobApplicationDAO.createJobApplication(jobApplication);

    }

    private Integer getEmployerId(Integer jobOfferId) {
        JobOffer jobOffer = jobOfferService.findJobOffer(jobOfferId);

        return jobOffer.getEmployer().getEmployerId();
    }

    public List<JobApplicationDTO> getCandidateApplications(Authentication authentication) {

        String candidateEmail = authentication.getName();
        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();

        List<JobApplication> jobApplications = jobApplicationDAO.findApplicationsByCandidateId(candidateId);

        // Mapa JobApplication na JobApplicationDTO
        return jobApplications.stream()
                .map(jobApplicationMapper::mapToDto)
                .collect(Collectors.toList());

    }

    public List<JobApplicationDTO> getAllJobApplicationsByEmployerId(Authentication authentication) {

        String employerEmail = authentication.getName();
        Integer employerId = employerService.findEmployerByEmail(employerEmail).getEmployerId();

        List<JobApplication> employerJobApplications = jobApplicationDAO.findEmployerJobApplicationsByEmployerId(employerId);



//
//        employerJobApplications.forEach(application -> {
//            if (application.getCandidate() == null) {
//                System.out.println("Candidate is null for application: " + application.getApplicationId());
//            }
//        });
        List<JobApplicationDTO> collect = employerJobApplications.stream()
                .map(jobApplicationMapper::mapToDto)
                .collect(Collectors.toList());

        System.out.println(collect);
        return collect;
    }
}
