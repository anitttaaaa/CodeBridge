package pl.zajavka.CodeBridge.business;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.ApplicationsHistoryDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.ApplicationsHistoryMapper;
import pl.zajavka.CodeBridge.api.dto.mapper.JobApplicationMapper;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatusEnum;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.business.dao.ApplicationsHistoryDAO;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.business.dao.JobApplicationDAO;
import pl.zajavka.CodeBridge.domain.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {
    private final CandidateService candidateService;

    private final JobApplicationDAO jobApplicationDAO;
    private final ApplicationsHistoryDAO applicationsHistoryDAO;
    private final CandidateDAO candidateDAO;
    private final JobOfferService jobOfferService;
    private final JobApplicationMapper jobApplicationMapper;
    private final ApplicationsHistoryMapper applicationsHistoryMapper;
    private final EmployerService employerService;

    @Autowired
    public JobApplicationService(CandidateService candidateService,
                                 JobApplicationDAO jobApplicationDAO,
                                 ApplicationsHistoryDAO applicationsHistoryDAO,
                                 CandidateDAO candidateDAO,
                                 JobOfferService jobOfferService,
                                 JobApplicationMapper jobApplicationMapper,
                                 ApplicationsHistoryMapper applicationsHistoryMapper,
                                 EmployerService employerService) {
        this.candidateService = candidateService;
        this.jobApplicationDAO = jobApplicationDAO;
        this.applicationsHistoryDAO = applicationsHistoryDAO;
        this.candidateDAO = candidateDAO;
        this.jobOfferService = jobOfferService;
        this.jobApplicationMapper = jobApplicationMapper;
        this.applicationsHistoryMapper = applicationsHistoryMapper;
        this.employerService = employerService;
    }

    @Transactional
    public void rejectJobApplication(Integer applicationId) {

        JobApplication jobApplication = jobApplicationDAO.findApplicationById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Job application not found for id: " + applicationId));

        JobApplication updatedJobApplication = new JobApplication.JobApplicationBuilder()
                .applicationId(jobApplication.getApplicationId())
                .jobOffer(jobApplication.getJobOffer())
                .employer(jobApplication.getEmployer())
                .candidate(jobApplication.getCandidate())
                .jobApplicationStatus(ApplicationStatusEnum.REJECTED)
                .build();

        jobApplicationDAO.save(updatedJobApplication);

        Candidate candidate = updatedJobApplication.getCandidate();
        Employer employer = updatedJobApplication.getEmployer();
        JobOffer jobOffer = updatedJobApplication.getJobOffer();
        ApplicationStatusEnum applicationStatusEnum = updatedJobApplication.getApplicationStatusEnum();

        ApplicationsHistory jobApplicationRejected = new ApplicationsHistory.Builder()
                .applicationHistoryId(applicationId)
                .jobOffer(jobOffer)
                .employer(employer)
                .candidate(candidate)
                .applicationStatus(applicationStatusEnum)
                .build();


        applicationsHistoryDAO.saveInHistory(jobApplicationRejected);

        jobApplicationDAO.deleteById(applicationId);
    }

    @Transactional
    public void acceptJobApplication(Integer applicationId) {

        JobApplication jobApplication = jobApplicationDAO.findApplicationById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Job application not found for id: " + applicationId));

        String candidateEmail = jobApplication.getCandidate().getEmail();
        Candidate candidateToUpdate = candidateService.findCandidateByEmail(candidateEmail);

        Candidate candidateUpdated = new Candidate.Builder()
                .candidateId(candidateToUpdate.getCandidateId())
                .name(candidateToUpdate.getName())
                .surname(candidateToUpdate.getSurname())
                .email(candidateToUpdate.getEmail())
                .phone(candidateToUpdate.getPhone())
                .status(StatusEnum.CURRENTLY_HIRED)
                .linkedIn(candidateToUpdate.getLinkedIn())
                .gitHub(candidateToUpdate.getGitHub())
                .techSpecialization(candidateToUpdate.getTechSpecialization())
                .aboutMe(candidateToUpdate.getAboutMe())
                .hobby(candidateToUpdate.getHobby())
                .userId(candidateToUpdate.getUserId())
                .profilePhoto(candidateToUpdate.getProfilePhoto())
                .candidateSkills(candidateToUpdate.getCandidateSkills())
                .candidateExperiences(candidateToUpdate.getCandidateExperiences())
                .candidateProjects(candidateToUpdate.getCandidateProjects())
                .candidateEducationStages(candidateToUpdate.getCandidateEducationStages())
                .candidateCourses(candidateToUpdate.getCandidateCourses())
                .build();

        candidateDAO.updateCandidate(candidateUpdated);

        jobApplication = new JobApplication.JobApplicationBuilder()
                .applicationId(jobApplication.getApplicationId())
                .jobOffer(jobApplication.getJobOffer())
                .employer(jobApplication.getEmployer())
                .candidate(jobApplication.getCandidate())
                .jobApplicationStatus(ApplicationStatusEnum.ACCEPTED)
                .build();


        jobApplicationDAO.save(jobApplication);

        Employer employer = jobApplication.getEmployer();
        JobOffer jobOffer = jobApplication.getJobOffer();
        ApplicationStatusEnum applicationStatusEnum = jobApplication.getApplicationStatusEnum();

        ApplicationsHistory jobApplicationAccepted = new ApplicationsHistory.Builder()
                .applicationHistoryId(applicationId)
                .jobOffer(jobOffer)
                .employer(employer)
                .candidate(candidateUpdated)
                .applicationStatus(applicationStatusEnum)
                .build();
        applicationsHistoryDAO.saveInHistory(jobApplicationAccepted);

        jobApplicationDAO.deleteById(applicationId);
    }


    @Transactional
    public void applyForJob(Integer jobOfferId, Authentication authentication) {

        String candidateEmail = authentication.getName();
        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();
        Integer employerId = getEmployerId(jobOfferId);

        ApplicationStatusEnum applicationStatusEnum = ApplicationStatusEnum.PENDING;

        JobOffer jobOffer = new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferId)
                .build();
        Employer employer = new Employer.EmployerBuilder()
                .employerId(employerId)
                .build();

        Candidate candidate = new Candidate.Builder()
                .candidateId(candidateId)
                .build();

        JobApplication jobApplication = new JobApplication.JobApplicationBuilder()
                .jobOffer(jobOffer)
                .employer(employer)
                .candidate(candidate)
                .jobApplicationStatus(applicationStatusEnum)
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

        return jobApplications.stream()
                .map(jobApplicationMapper::mapToDto)
                .sorted(Comparator.comparingInt(JobApplicationDTO::getApplicationId).reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<JobApplicationDTO> getAllJobApplicationsByEmployerId(Authentication authentication) {
        String employerEmail = authentication.getName();

        Integer employerId = employerService.findEmployerByEmail(employerEmail).getEmployerId();

        List<JobApplication> employerJobApplications = jobApplicationDAO.findEmployerJobApplicationsByEmployerId(employerId);

        return employerJobApplications.stream()
                .map(jobApplicationMapper::mapToDto)
                .sorted(Comparator.comparingInt(JobApplicationDTO::getApplicationId).reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ApplicationsHistoryDTO> getAllEmployerHistoryJobApplications(Authentication authentication) {

        String employerEmail = authentication.getName();

        Integer employerId = employerService.findEmployerByEmail(employerEmail).getEmployerId();

        List<ApplicationsHistory> employerHistoryApplications = jobApplicationDAO.findEmployerHistoryApplicationsByEmployerId(employerId);

        return employerHistoryApplications.stream()
                .map(applicationsHistoryMapper::mapToDto)
                .sorted(Comparator.comparing(ApplicationsHistoryDTO::getApplicationHistoryId).reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ApplicationsHistoryDTO> getAllCandidateHistoryJobApplications(Authentication authentication) {

        String candidateEmail = authentication.getName();

        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();

        List<ApplicationsHistory> candidateHistoryApplications = jobApplicationDAO.findCandidateHistoryApplicationsByCandidateId(candidateId);

        return candidateHistoryApplications.stream()
                .map(applicationsHistoryMapper::mapToDto)
                .sorted(Comparator.comparing(ApplicationsHistoryDTO::getApplicationHistoryId).reversed())
                .collect(Collectors.toList());
    }
}
