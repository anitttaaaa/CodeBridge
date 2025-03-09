package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import pl.zajavka.CodeBridge.api.enums.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    public JobOfferEntity(Integer jobOfferId, String jobOfferTitle, String description, List<String> mustHaveSkills, List<String> niceToHaveSkills) {

        this.jobOfferId = jobOfferId;
        this.jobOfferTitle = jobOfferTitle;
        this.description = description;
        this.mustHaveSkills = mustHaveSkills;
        this.niceToHaveSkills = niceToHaveSkills;

    }

    public JobOfferEntity(Integer jobOfferId, String jobOfferTitle, String description, EmployerEntity employerEntity) {
        this.jobOfferId = jobOfferId;
        this.jobOfferTitle = jobOfferTitle;
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOfferEntity that = (JobOfferEntity) o;
        return Objects.equals(jobOfferId, that.jobOfferId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobOfferId);
    }

    @Override
    public String toString() {
        return "JobOfferEntity{" +
                "jobOfferId=" + jobOfferId +
                ", jobOfferTitle='" + jobOfferTitle + '\'' +
                ", description='" + description + '\'' +
                ", techSpecialization=" + techSpecialization +
                ", workType=" + workType +
                ", city=" + city +
                ", experience=" + experience +
                ", salary=" + salary +
                ", employer=" + employer +
                ", jobApplications=" + jobApplications +
                ", mustHaveSkills=" + mustHaveSkills +
                ", niceToHaveSkills=" + niceToHaveSkills +
                '}';
    }

    public JobOfferEntity() {
    }

    public JobOfferEntity(Integer jobOfferId, String jobOfferTitle, String description,
                          TechSpecializationsEnum techSpecialization, WorkTypesEnum workType,
                          CitiesEnum city, ExperiencesEnum experience, SalaryRangeEnum salary,
                          EmployerEntity employer, Set<JobApplicationEntity> jobApplications,
                          List<String> mustHaveSkills, List<String> niceToHaveSkills) {
        this.jobOfferId = jobOfferId;
        this.jobOfferTitle = jobOfferTitle;
        this.description = description;
        this.techSpecialization = techSpecialization;
        this.workType = workType;
        this.city = city;
        this.experience = experience;
        this.salary = salary;
        this.employer = employer;
        this.jobApplications = jobApplications;
        this.mustHaveSkills = mustHaveSkills;
        this.niceToHaveSkills = niceToHaveSkills;
    }

    public Integer getJobOfferId() {
        return jobOfferId;
    }

    public String getJobOfferTitle() {
        return jobOfferTitle;
    }

    public String getDescription() {
        return description;
    }

    public TechSpecializationsEnum getTechSpecialization() {
        return techSpecialization;
    }

    public WorkTypesEnum getWorkType() {
        return workType;
    }

    public CitiesEnum getCity() {
        return city;
    }

    public ExperiencesEnum getExperience() {
        return experience;
    }

    public SalaryRangeEnum getSalary() {
        return salary;
    }

    public EmployerEntity getEmployer() {
        return employer;
    }

    public Set<JobApplicationEntity> getJobApplications() {
        return jobApplications;
    }

    public List<String> getMustHaveSkills() {
        return mustHaveSkills;
    }

    public List<String> getNiceToHaveSkills() {
        return niceToHaveSkills;
    }
}


