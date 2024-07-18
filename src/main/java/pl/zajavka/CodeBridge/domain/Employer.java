package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "employerId")
@ToString(of = {"employerId", "companyName", "email", "nip"})
public class Employer {

    Integer employerId;
    String companyName;
    String email;
    String nip;
    Integer userId;
    Set<JobOffer> jobOffers;


    public Set<JobOffer> getJobOffers () {
        return Objects.isNull(jobOffers) ? new HashSet<>() : jobOffers;
    }



}
