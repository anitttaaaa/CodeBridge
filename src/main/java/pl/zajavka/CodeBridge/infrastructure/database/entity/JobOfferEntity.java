package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.zajavka.CodeBridge.api.enums.*;

import java.util.List;

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


    @Enumerated(EnumType.STRING)
    @Column(name = "tech_specialization")
    private TechSpecializationsEnum techSpecialization;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_type")
    private WorkTypesEnum workType;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private CitiesEnum city;

    @Enumerated(EnumType.STRING)
    @Column(name = "experience")
    private ExperiencesEnum experience;


    @Enumerated(EnumType.STRING)
    @Column(name = "salary")
    private SalaryRangeEnum salary;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private EmployerEntity employer;
}
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "tech_specialization_id")
//    private TechSpecializationEntity techSpecialization;


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



