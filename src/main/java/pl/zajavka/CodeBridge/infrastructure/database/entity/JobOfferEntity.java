package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.zajavka.CodeBridge.api.enums.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "jobOfferId")
@ToString(of = {"jobOfferId", "jobOfferTitle"})
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

    @Column(name = "job_offer_title")
    private String jobOfferTitle;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobOffer")
    private Set<JobApplicationEntity> jobApplications;

    @ElementCollection
    @CollectionTable(name = "job_offer_must_have_skills", joinColumns = @JoinColumn(name = "job_offer_id"))
    @Column(name = "must_have_skills")
    private List<String> mustHaveSkills;

    @ElementCollection
    @CollectionTable(name = "job_offer_nice_to_have_skills", joinColumns = @JoinColumn(name = "job_offer_id"))
    @Column(name = "nice_to_have_skills")
    private List<String> niceToHaveSkills;
}


