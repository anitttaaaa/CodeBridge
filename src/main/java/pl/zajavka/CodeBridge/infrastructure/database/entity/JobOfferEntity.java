package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "jobOfferId")
@ToString(of = {"jobOfferId", "title"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_offer")
public class JobOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_offer_id")
    private Integer jobOfferId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<JobOfferCategoryEntity> jobOfferCategories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private EmployerEntity employer;
}

//    @Column(name = "job_offer_code", unique = true)
//    private String jobOfferCode;

//    @Column(name = "posted_date")
//    private OffsetDateTime postedDate;
//
//    @Column(name = "expiry_date")
//    private OffsetDateTime expiryDate;
//

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "location_id")
//    private LocationEntity job_location;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "salary_id")
//    private SalaryEntity salary;
//

//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobOffer")
//    private Set<JobOfferSkillEntity> jobOfferSkills;



