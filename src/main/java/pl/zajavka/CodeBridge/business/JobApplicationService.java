package pl.zajavka.CodeBridge.business;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.JobApplicationMapper;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.business.dao.ApplicationsHistoryDAO;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.business.dao.JobApplicationDAO;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobApplicationService {

    private final CandidateService candidateService;
    private final JobApplicationDAO jobApplicationDAO;
    private final ApplicationsHistoryDAO applicationsHistoryDAO;
    private final CandidateDAO candidateDAO;
    private final JobOfferService jobOfferService;
    private final JobApplicationMapper jobApplicationMapper;
    private final EmployerService employerService;

    @Transactional
    public void acceptJobApplication(Integer applicationId, Authentication authentication) {


        JobApplication jobApplication = jobApplicationDAO.findApplicationById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Job application not found for id: " + applicationId));

        jobApplicationDAO.save(jobApplication.withApplicationStatus(ApplicationStatus.ACCEPTED));

        Candidate updatedCandidate = jobApplication.getCandidate()
                .withStatus(StatusEnum.HIRED.getDescription());
        candidateDAO.updateCandidate(updatedCandidate);



        Candidate candidate = jobApplication.getCandidate();
        Employer employer = jobApplication.getEmployer();
        JobOffer jobOffer = jobApplication.getJobOffer();
        ApplicationStatus applicationStatus = jobApplication.getApplicationStatus();


        ApplicationsHistory jobApplicationAccepted = ApplicationsHistory.builder()
                .applicationHistoryId(applicationId)
                .jobOffer(jobOffer)
                .candidate(candidate)
                .employer(employer)
                .applicationStatus(applicationStatus)
                .build();

        applicationsHistoryDAO.save(jobApplicationAccepted);

        jobApplicationDAO.deleteById(applicationId);

    }


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

        List<JobApplicationDTO> collect = employerJobApplications.stream()
                .map(jobApplicationMapper::mapToDto)
                .collect(Collectors.toList());

        return collect;
    }
}
