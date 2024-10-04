package pl.zajavka.CodeBridge.business;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.domain.JobOfferAdd;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.Set;

@Service
@AllArgsConstructor
public class JobOfferAddService {

    private final CodeBridgeUserDetailsService codeBridgeUserDetailsService;
    private final EmployerService employerService;


    @Transactional
    public void createJobOfferData(JobOfferAdd request, Authentication authentication) {

        String username = authentication.getName();
        Integer userId = codeBridgeUserDetailsService.getUserId(username);
        Employer employer = employerService.findEmployer(userId);

        JobOffer jobOffer = buildJobOffer(request);

        Set<JobOffer> jobOffers = employer.getJobOffers();

        jobOffers.add(jobOffer);

        Employer employerAndJobOffer = employer.withJobOffers(jobOffers);

        employerService.createJobOffer(employerAndJobOffer);
    }


    private JobOffer buildJobOffer(JobOfferAdd request) {
        return JobOffer.builder()
                .title(request.getJobOfferTitle())
                .description(request.getDescription())
                .techSpecialization(request.getTechSpecialization())
                .workType(request.getWorkType())
                .city(request.getCity())
                .experience(request.getExperience())
                .salary(request.getSalary())
                .mustHaveSkills(request.getMustHaveSkills())
                .niceToHaveSkills(request.getNiceToHaveSkills())
                .build();
    }
}
