package pl.zajavka.CodeBridge.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.dto.mapper.EmployerMapper;
import pl.zajavka.CodeBridge.api.dto.mapper.JobOfferMapper;
import pl.zajavka.CodeBridge.api.enums.*;
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
public class EmployerPortalController {

    private static final String GET_EMPLOYER = "/employer-portal";
    private static final String GET_EMPLOYER_VIEW_CANDIDATE_PROFILE = "/employer-portal/job-applications/candidate-profile";
    private static final String GET_CANDIDATE_PROFILE_PHOTO = "/images/profile-photo";
    private static final String GET_EMPLOYER_NEW_JOB_OFFER_FORM = "/employer-portal/new-job-offer";
    private static final String GET_EMPLOYER_MY_JOB_OFFERS = "/employer-portal/my-job-offers";
    private static final String GET_ALL_CANDIDATES = "/employer-portal/find-a-candidate";
    private static final String GET_FILTERED_CANDIDATES = "/employer-portal/find-a-candidate/filtered-candidates";

    private static final String ADD_EMPLOYER_NEW_JOB_OFFER = "/employer-portal/new-job-offer/add";

    private final JobOfferService jobOfferService;
    private final EmployerService employerService;
    private final CandidateService candidateService;


    public EmployerPortalController(JobOfferService jobOfferService,
                                    EmployerService employerService,
                                    CandidateService candidateService) {
        this.jobOfferService = jobOfferService;
        this.employerService = employerService;
        this.candidateService = candidateService;
    }


    @GetMapping(value = GET_EMPLOYER)
    public String employerPortal(Model model) {
        EmployerDTO employerDetails = employerService.getLoggedInEmployerDetails();
        model.addAttribute("employer", employerDetails);
        return "employer_portal";
    }

    @GetMapping(GET_CANDIDATE_PROFILE_PHOTO)
    public ResponseEntity<byte[]> getCandidateProfilePhoto(
            @RequestParam("email") String email) {
        byte[] profilePhoto = employerService.getCandidateProfilePhoto(email);

        if (profilePhoto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(profilePhoto);
    }

    @PostMapping(GET_EMPLOYER_VIEW_CANDIDATE_PROFILE)
    public String getEmployerCandidateDetails(
            @RequestParam String email,
            Model model) {

        CandidateDTO candidateProfileDTO = candidateService.getCandidateDetailsByEmployer(email);

        model.addAttribute("candidateDetails", candidateProfileDTO);
        model.addAttribute("candidateExperiences", candidateProfileDTO.getCandidateExperiences());
        model.addAttribute("candidateProjects", candidateProfileDTO.getCandidateProjects());
        model.addAttribute("candidateEducationStages", candidateProfileDTO.getCandidateEducationStages());
        model.addAttribute("candidateCourses", candidateProfileDTO.getCandidateCourses());

        return "employer_view_candidate_profile";
    }


    @GetMapping(GET_EMPLOYER_MY_JOB_OFFERS)
    public String getAllMyJobOffers(Authentication authentication, Model model) {

        List<JobOfferDTO> employerJobOffers = jobOfferService.getSortedJobOffersByEmployerId(authentication);

        model.addAttribute("employerJobOffers", employerJobOffers);

        return "employer_portal_my_job_offers";
    }


    @GetMapping(GET_ALL_CANDIDATES)
    public String getAllCandidates(Model model) {

        List<CandidateDTO> allCandidatesDTO = employerService.getAllCandidates();

        model.addAttribute("allCandidates", allCandidatesDTO);

        return "employer_portal_find_a_candidate";
    }

    @GetMapping(GET_FILTERED_CANDIDATES)
    public String getFilteredCandidates(
            @RequestParam(required = false) TechSpecializationsEnum techSpecialization,
            @RequestParam(required = false) StatusEnum status,
            Model model) {

        List<CandidateDTO> filteredCandidatesDTO = employerService.getFilteredCandidates(techSpecialization, status);

        model.addAttribute("allCandidates", filteredCandidatesDTO);

        return "employer_portal_find_a_candidate";
    }


    @GetMapping(value = GET_EMPLOYER_NEW_JOB_OFFER_FORM)
    public String showJobOfferForm(Model model) {

        JobOfferDTO jobOfferDTO = jobOfferService.createNewJobOfferDTO();

        model.addAttribute("jobOfferDTO", jobOfferDTO);

        return "employer_portal_new_job_offer";
    }

    @PostMapping(ADD_EMPLOYER_NEW_JOB_OFFER)
    public String addJobOffer(
            @RequestParam("jobOfferTitle") String jobOfferTitle,
            @RequestParam("description") String description,
            @RequestParam("techSpecialization") String techSpecialization,
            @RequestParam("workType") String workType,
            @RequestParam("city") String city,
            @RequestParam("experience") String experience,
            @RequestParam("salary") String salary,
            @RequestParam List<SkillsEnum> mustHaveSkills,
            @RequestParam List<SkillsEnum> niceToHaveSkills,
            Authentication authentication) {

        jobOfferService.createJobOfferData(
                jobOfferTitle, description, techSpecialization, workType, city,
                experience, salary, mustHaveSkills, niceToHaveSkills, authentication);

        return "redirect:/employer-portal/my-job-offers";
    }

}


