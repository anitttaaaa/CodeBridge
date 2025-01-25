package pl.zajavka.CodeBridge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.api.dto.mapper.JobOfferMapper;
import pl.zajavka.CodeBridge.business.CandidateService;
import pl.zajavka.CodeBridge.business.EmployerService;
import pl.zajavka.CodeBridge.business.JobOfferService;
import pl.zajavka.CodeBridge.domain.Candidate;
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
    private final CandidateMapper candidateMapper;
    private final JobOfferService jobOfferService;
    private final EmployerService employerService;
    private final CandidateService candidateService;


    @GetMapping(GET_EMPLOYER_VIEW_CANDIDATE_PROFILE)
    public String getEmployerCandidateDetails(
            @RequestParam("candidateId") Integer candidateId,
            Model model) {

        CandidateDTO candidateDetails = candidateService.findCandidateByCandidateId(candidateId);
        model.addAttribute("candidateDetails", candidateDetails);




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

    @GetMapping(value = GET_EMPLOYER)
    public String employerPortal() {
        return "employer_portal";
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
        List<Candidate> filteredCandidates = employerService.getFilteredCandidates(techSpecialization, status)
                .stream().sorted(Comparator.comparingInt(Candidate::getCandidateId).reversed())
                .collect(Collectors.toList());
        ;

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


