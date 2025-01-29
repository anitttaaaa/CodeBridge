package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.dto.mapper.EmployerMapper;
import pl.zajavka.CodeBridge.api.dto.mapper.JobOfferMapper;
import pl.zajavka.CodeBridge.business.CandidateService;
import pl.zajavka.CodeBridge.business.EmployerService;
import pl.zajavka.CodeBridge.business.JobOfferService;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class EmployerPortalController {

    private static final String GET_EMPLOYER = "/employer-portal";
    private static final String GET_EMPLOYER_VIEW_CANDIDATE_PROFILE = "/employer-portal/job-applications/candidate-profile";
    private static final String GET_EMPLOYER_NEW_JOB_OFFER_FORM = "/employer-portal/new-job-offer";
    private static final String GET_EMPLOYER_MY_JOB_OFFERS = "/employer-portal/my-job-offers";
    private static final String GET_ALL_CANDIDATES = "/employer-portal/find-a-candidate";
    private static final String GET_FILTERED_CANDIDATES = "/employer-portal/find-a-candidate/filtered-candidates";

    private static final String ADD_EMPLOYER_NEW_JOB_OFFER = "/employer-portal/new-job-offer/add";

    private final JobOfferMapper jobOfferMapper;
    private final EmployerMapper employerMapper;
    private final JobOfferService jobOfferService;
    private final EmployerService employerService;
    private final CandidateService candidateService;


    @GetMapping(value = GET_EMPLOYER)
    public String employerPortal(Model model) {

        Employer employer = employerService.findLoggedInEmployer();
        EmployerDTO employerDetails = employerMapper.mapToDto(employer);

        model.addAttribute("employer", employerDetails);


        return "employer_portal";
    }


    @GetMapping("/images/{candidateId}/profile-photo")
    public ResponseEntity<byte[]> getProfilePhoto(
            @PathVariable("candidateId") Integer candidateId) {

        CandidateDTO candidateDetails = candidateService.findCandidateByCandidateId(candidateId);

        byte[] profilePhoto = candidateDetails.getProfilePhoto();

        if (profilePhoto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(profilePhoto);
    }

    @GetMapping(GET_EMPLOYER_VIEW_CANDIDATE_PROFILE)
    public String getEmployerCandidateDetails(
            @RequestParam("candidateId") Integer candidateId,
            Model model) {

        CandidateDTO candidateDetails = candidateService.findCandidateByCandidateId(candidateId);

        List<CandidateExperienceDTO> sortedExperiences = candidateDetails.getCandidateExperiences()
                .stream()
                .sorted(Comparator.comparing(CandidateExperienceDTO::getFromDate))
                .toList();

        List<CandidateProjectDTO> sortedProjects = candidateDetails.getCandidateProjects()
                .stream()
                .sorted(Comparator.comparing(CandidateProjectDTO::getFromDate))
                .toList();

        List<CandidateEducationDTO> sortedEducationStages = candidateDetails.getCandidateEducationStages()
                .stream()
                .sorted(Comparator.comparing(CandidateEducationDTO::getFromDate))
                .toList();

        List<CandidateCourseDTO> sortedCourses = candidateDetails.getCandidateCourses()
                .stream()
                .sorted(Comparator.comparing(CandidateCourseDTO::getFromDate))
                .toList();

        model.addAttribute("candidateDetails", candidateDetails);
        model.addAttribute("candidateExperiences", sortedExperiences);
        model.addAttribute("candidateProjects", sortedProjects);
        model.addAttribute("candidateEducationStages", sortedEducationStages);
        model.addAttribute("candidateCourses", sortedCourses);


        return "/employer_view_candidate_profile";
    }


    @GetMapping(GET_EMPLOYER_MY_JOB_OFFERS)
    public String getAllMyJobOffers(Authentication authentication,
                                    Model model) {

        List<JobOfferDTO> employerJobOffers = jobOfferService.getJobOffersByEmployerId(authentication).stream()
                .sorted(Comparator.comparingInt(JobOfferDTO::getJobOfferId).reversed())
                .collect(Collectors.toList());

        model.addAttribute("employerJobOffers", employerJobOffers);


        return "employer_portal_my_job_offers";
    }


    @GetMapping(GET_ALL_CANDIDATES)
    public String getAllCandidates(Model model) {

        List<Candidate> allCandidates = employerService.getAllCandidates().stream()
                .sorted(Comparator.comparingInt(Candidate::getCandidateId).reversed())
                .collect(Collectors.toList());

        model.addAttribute("allCandidates", allCandidates);


        return "employer_portal_find_a_candidate";
    }

    @GetMapping(GET_FILTERED_CANDIDATES)
    public String getFilteredCandidates(
            @RequestParam(required = false) String techSpecialization,
            @RequestParam(required = false) String status,
            Model model) {

        if (techSpecialization != null && techSpecialization.isEmpty()) {
            techSpecialization = null;
        }
        if (status != null && status.isEmpty()) {
            status = null;
        }

        List<Candidate> filteredCandidates = employerService.getFilteredCandidates(techSpecialization, status)
                .stream().sorted(Comparator.comparingInt(Candidate::getCandidateId).reversed())
                .collect(Collectors.toList());

        model.addAttribute("allCandidates", filteredCandidates);

        return "employer_portal_find_a_candidate";
    }


    @GetMapping(value = GET_EMPLOYER_NEW_JOB_OFFER_FORM)
    public String showJobOfferForm(Model model) {
        model.addAttribute("jobOfferDTO", new JobOfferDTO());
        return "employer_portal_new_job_offer";
    }

    @PostMapping(ADD_EMPLOYER_NEW_JOB_OFFER)
    public String addJobOffer(
            @ModelAttribute("jobOfferDTO") JobOfferDTO jobOfferDTO,
            Authentication authentication) {

        JobOffer request = jobOfferMapper.mapToDomain(jobOfferDTO);
        jobOfferService.createJobOfferData(request, authentication);


        return "redirect:/employer-portal";
    }


}


