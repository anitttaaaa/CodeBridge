package pl.zajavka.CodeBridge.business;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;
import pl.zajavka.CodeBridge.business.dao.JobApplicationDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

@Service
@AllArgsConstructor
public class JobApplicationService {

    CandidateService candidateService;
    JobApplicationDAO jobApplicationDAO;
    JobOfferService jobOfferService;
    CodeBridgeUserDetailsService codeBridgeUserDetailsService;

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
}
