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
    private SalaryEnum salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private EmployerEntity employer;

    @OneToMany(mappedBy = "jobOffer")
    private Set<JobApplicationEntity> jobApplications;

    @ElementCollection
    @CollectionTable(name = "job_offer_must_have_skills", joinColumns = @JoinColumn(name = "job_offer_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "must_have_skills")
    private List<String> mustHaveSkills;

    @ElementCollection
    @CollectionTable(name = "job_offer_nice_to_have_skills", joinColumns = @JoinColumn(name = "job_offer_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "nice_to_have_skills")
    private List<String> niceToHaveSkills;


    public JobOfferEntity() {
    }

    private JobOfferEntity(Builder builder) {
        this.jobOfferId = builder.jobOfferId;
        this.jobOfferTitle = builder.jobOfferTitle;
        this.description = builder.description;
        this.techSpecialization = builder.techSpecialization;
        this.workType = builder.workType;
        this.city = builder.city;
        this.experience = builder.experience;
        this.salary = builder.salary;
        this.employer = builder.employer;
        this.jobApplications = builder.jobApplications;
        this.mustHaveSkills = builder.mustHaveSkills;
        this.niceToHaveSkills = builder.niceToHaveSkills;
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
    public static JobOfferEntity.Builder builder() {

        return new JobOfferEntity.Builder();
    }
    public static class Builder {
        private Integer jobOfferId;
        private String jobOfferTitle;
        private String description;
        private TechSpecializationsEnum techSpecialization;
        private WorkTypesEnum workType;
        private CitiesEnum city;
        private ExperiencesEnum experience;
        private SalaryEnum salary;
        private EmployerEntity employer;
        private Set<JobApplicationEntity> jobApplications;
        private List<String> mustHaveSkills;
        private List<String> niceToHaveSkills;

        public Builder jobOfferId(Integer jobOfferId) {
            this.jobOfferId = jobOfferId;
            return this;
        }

        public Builder jobOfferTitle(String jobOfferTitle) {
            this.jobOfferTitle = jobOfferTitle;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder techSpecialization(TechSpecializationsEnum techSpecialization) {
            this.techSpecialization = techSpecialization;
            return this;
        }

        public Builder workType(WorkTypesEnum workType) {
            this.workType = workType;
            return this;
        }

        public Builder city(CitiesEnum city) {
            this.city = city;
            return this;
        }

        public Builder experience(ExperiencesEnum experience) {
            this.experience = experience;
            return this;
        }

        public Builder salary(SalaryEnum salary) {
            this.salary = salary;
            return this;
        }

        public Builder employer(EmployerEntity employer) {
            this.employer = employer;
            return this;
        }

        public Builder jobApplications(Set<JobApplicationEntity> jobApplications) {
            this.jobApplications = jobApplications;
            return this;
        }

        public Builder mustHaveSkills(List<String> mustHaveSkills) {
            this.mustHaveSkills = mustHaveSkills;
            return this;
        }

        public Builder niceToHaveSkills(List<String> niceToHaveSkills) {
            this.niceToHaveSkills = niceToHaveSkills;
            return this;
        }

        public JobOfferEntity build() {
            return new JobOfferEntity(this);
        }
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

    public SalaryEnum getSalary() {
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
