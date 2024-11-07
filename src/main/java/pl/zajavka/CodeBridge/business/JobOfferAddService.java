package pl.zajavka.CodeBridge.business;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOfferToDatabase;
import pl.zajavka.CodeBridge.domain.JobOfferFromRequest;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.Set;

@Service
@AllArgsConstructor
public class JobOfferAddService {

    private final CodeBridgeUserDetailsService codeBridgeUserDetailsService;
    private final EmployerService employerService;


    @Transactional
    public void createJobOfferData(JobOfferFromRequest request, Authentication authentication) {

        String username = authentication.getName();
        Integer userId = codeBridgeUserDetailsService.getUserId(username);
        Employer employer = employerService.findEmployer(userId);

        JobOfferToDatabase jobOffer = buildJobOffer(request);

        Set<JobOfferToDatabase> jobOffers = employer.getJobOffers();

        jobOffers.add(jobOffer);

        Employer employerAndJobOffer = employer.withJobOffers(jobOffers);

        employerService.createJobOffer(employerAndJobOffer);
    }


    private JobOfferToDatabase buildJobOffer(JobOfferFromRequest request) {
        return JobOfferToDatabase.builder()
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
