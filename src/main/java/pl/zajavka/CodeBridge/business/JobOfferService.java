package pl.zajavka.CodeBridge.business;


import com.lowagie.text.pdf.PRAcroForm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.business.dao.JobOfferDAO;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.security.CodeBridgeUserDetailsService;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class JobOfferService {

    private final CodeBridgeUserDetailsService codeBridgeUserDetailsService;
    private final EmployerService employerService;
    private final JobOfferDAO jobOfferDAO;


    @Transactional
    public void createJobOfferData(JobOffer request, Authentication authentication) {

        String username = authentication.getName();
        Integer userId = codeBridgeUserDetailsService.getUserId(username);
        Employer employer = employerService.findEmployer(userId);

        JobOffer jobOffer = buildJobOffer(request);

        Set<JobOffer> jobOffers = employer.getJobOffers();

        jobOffers.add(jobOffer);

        Employer employerAndJobOffer = employer.withJobOffers(jobOffers);

        employerService.createJobOffer(employerAndJobOffer);
    }


    private JobOffer buildJobOffer(JobOffer request) {
        return JobOffer.builder()
                .jobOfferTitle(request.getJobOfferTitle())
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

    @Transactional
    public List<JobOffer> getAllJobOffers() {

        return jobOfferDAO.findAllJobOffers();

    }
}
