package pl.zajavka.CodeBridge.business;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.ApplicationsHistoryDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.ApplicationsHistoryMapper;
import pl.zajavka.CodeBridge.api.dto.mapper.JobApplicationMapper;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.business.dao.ApplicationsHistoryDAO;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.business.dao.JobApplicationDAO;
import pl.zajavka.CodeBridge.domain.*;

import java.util.Collections;
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
    public void rejectJobApplication(Integer applicationId, Authentication authentication) {

        // Znalezienie aplikacji
        JobApplication jobApplication = jobApplicationDAO.findApplicationById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Job application not found for id: " + applicationId));

        // Utworzenie nowego obiektu JobApplication z nowym statusem REJECTED
        JobApplication updatedJobApplication = new JobApplication(
                jobApplication.getApplicationId(),  // ID aplikacji (zachowane)
                jobApplication.getJobOffer(),        // Oferta pracy
                jobApplication.getEmployer(),        // Pracodawca
                jobApplication.getCandidate(),       // Kandydat
                ApplicationStatus.REJECTED           // Nowy status
        );

        // Zapisanie zaktualizowanej aplikacji
        jobApplicationDAO.save(updatedJobApplication);

        // Pobranie danych do historii aplikacji
        Candidate candidate = updatedJobApplication.getCandidate();
        Employer employer = updatedJobApplication.getEmployer();
        JobOffer jobOffer = updatedJobApplication.getJobOffer();
        ApplicationStatus applicationStatus = updatedJobApplication.getApplicationStatus();

        // Utworzenie wpisu do historii
        ApplicationsHistory jobApplicationRejected = new ApplicationsHistory(
                applicationId,
                jobOffer,
                employer,
                candidate,
                applicationStatus
        );

        // Zapisanie historii aplikacji
        applicationsHistoryDAO.saveInHistory(jobApplicationRejected);

        // Usunięcie aplikacji z bazy (jeśli wymagane)
        jobApplicationDAO.deleteById(applicationId);
    }

    @Transactional
    public void acceptJobApplication(Integer applicationId, Authentication authentication) {

        // Znalezienie aplikacji
        JobApplication jobApplication = jobApplicationDAO.findApplicationById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Job application not found for id: " + applicationId));

        // Znalezienie kandydata na podstawie e-maila
        String candidateEmail = jobApplication.getCandidate().getEmail();
        Candidate candidateToUpdate = candidateService.findCandidateByEmail(candidateEmail);

        Candidate candidateUpdated = new Candidate.Builder()
                .candidateId(candidateToUpdate.getCandidateId())
                .name(candidateToUpdate.getName())
                .surname(candidateToUpdate.getSurname())
                .email(candidateToUpdate.getEmail())
                .phone(candidateToUpdate.getPhone())
                .status(StatusEnum.HIRED.getDescription())
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

        // Aktualizacja kandydata
        candidateDAO.updateCandidate(candidateUpdated);

        // Utworzenie nowego obiektu JobApplication z nowym statusem "ACCEPTED"
        jobApplication = new JobApplication(
                jobApplication.getApplicationId(), // ID aplikacji (zachowane)
                jobApplication.getJobOffer(),       // Oferta pracy
                jobApplication.getEmployer(),       // Pracodawca
                jobApplication.getCandidate(),      // Kandydat
                ApplicationStatus.ACCEPTED          // Nowy status aplikacji
        );

        // Zapisanie zaktualizowanej aplikacji
        jobApplicationDAO.save(jobApplication);

        // Pobranie danych do historii aplikacji
        Employer employer = jobApplication.getEmployer();
        JobOffer jobOffer = jobApplication.getJobOffer();
        ApplicationStatus applicationStatus = jobApplication.getApplicationStatus();

        // Utworzenie wpisu do historii aplikacji
        ApplicationsHistory jobApplicationAccepted = new ApplicationsHistory(
                applicationId,
                jobOffer,
                employer,
                candidateUpdated,
                applicationStatus
        );

        // Zapisanie historii aplikacji
        applicationsHistoryDAO.saveInHistory(jobApplicationAccepted);

        // Usunięcie aplikacji po akceptacji
        jobApplicationDAO.deleteById(applicationId);
    }


    @Transactional
    public void applyForJob(Integer jobOfferId, Authentication authentication) {

        String candidateEmail = authentication.getName();
        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();
        Integer employerId = getEmployerId(jobOfferId);

        ApplicationStatus applicationStatus = ApplicationStatus.PENDING;

        JobOffer jobOfferId1 = new JobOffer(jobOfferId);
        Employer employerId1 = new Employer(employerId);
        Candidate candidateId1 = new Candidate.Builder()
                .candidateId(candidateId)
                .build();


        // Tworzenie JobApplication za pomocą konstruktorów
        JobApplication jobApplication = new JobApplication(jobOfferId1, employerId1, candidateId1, applicationStatus);

        // Zapisanie aplikacji do bazy danych
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

    public List<ApplicationsHistoryDTO> getAllEmployerHistoryJobApplications(Authentication authentication) {

        String employerEmail = authentication.getName();
        Integer employerId = employerService.findEmployerByEmail(employerEmail).getEmployerId();

        List<ApplicationsHistory> employerHistoryApplications = jobApplicationDAO.findEmployerHistoryApplicationsByEmployerId(employerId);

        List<ApplicationsHistoryDTO> collect = employerHistoryApplications.stream()
                .map(applicationsHistoryMapper::mapToDto)
                .collect(Collectors.toList());

        return collect;
    }

    public List<ApplicationsHistoryDTO> getAllCandidateHistoryJobApplications(Authentication authentication) {

        String candidateEmail = authentication.getName();
        Integer candidateId = candidateService.findCandidateByEmail(candidateEmail).getCandidateId();



        List<ApplicationsHistory> candidateHistoryApplications = jobApplicationDAO.findCandidateHistoryApplicationsByCandidateId(candidateId);
        // Zapewnienie, że lista nie będzie null
        if (candidateHistoryApplications == null) {
            return Collections.emptyList();
        }

        List<ApplicationsHistoryDTO> collect = candidateHistoryApplications.stream()
                .map(applicationsHistoryMapper::mapToDto)
                .collect(Collectors.toList());

        return collect;
    }
}
