package pl.zajavka.CodeBridge.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "employerId")
@ToString (of = {"employerId", "companyName", "email"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "employer")
public class EmployerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private Integer employerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nip")
    private String nip;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employer")
    private Set<JobOfferEntity> jobOffers;


}
