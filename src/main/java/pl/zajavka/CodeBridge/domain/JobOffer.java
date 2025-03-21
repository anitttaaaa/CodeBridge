package pl.zajavka.CodeBridge.domain;

import pl.zajavka.CodeBridge.api.enums.*;

import java.util.List;
import java.util.Objects;

public class JobOffer {

    private final Integer jobOfferId;
    private final String jobOfferTitle;
    private final String description;
    private final TechSpecializationsEnum techSpecialization;
    private final Employer employer;
    private final WorkTypesEnum workType;
    private final CitiesEnum city;
    private final ExperiencesEnum experience;
    private final SalaryEnum salary;
    private final List<SkillsEnum> mustHaveSkills;
    private final List<SkillsEnum> niceToHaveSkills;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOffer jobOffer = (JobOffer) o;
        return Objects.equals(jobOfferId, jobOffer.jobOfferId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobOfferId);
    }

    @Override
    public String toString() {
        return "JobOffer{" +
                "jobOfferId=" + jobOfferId +
                ", jobOfferTitle='" + jobOfferTitle + '\'' +
                ", description='" + description + '\'' +
                ", techSpecialization=" + techSpecialization +
                ", employer=" + employer +
                ", workType=" + workType +
                ", city=" + city +
                ", experience=" + experience +
                ", salary=" + salary +
                ", mustHaveSkills=" + mustHaveSkills +
                ", niceToHaveSkills=" + niceToHaveSkills +
                '}';
    }

    private JobOffer(JobOfferBuilder builder) {
        this.jobOfferId = builder.jobOfferId;
        this.jobOfferTitle = builder.jobOfferTitle;
        this.description = builder.description;
        this.techSpecialization = builder.techSpecialization;
        this.employer = builder.employer;
        this.workType = builder.workType;
        this.city = builder.city;
        this.experience = builder.experience;
        this.salary = builder.salary;
        this.mustHaveSkills = builder.mustHaveSkills;
        this.niceToHaveSkills = builder.niceToHaveSkills;
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

    public Employer getEmployer() {
        return employer;
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

    public List<SkillsEnum> getMustHaveSkills() {
        return mustHaveSkills;
    }

    public List<SkillsEnum> getNiceToHaveSkills() {
        return niceToHaveSkills;
    }

    public static class JobOfferBuilder {

        private Integer jobOfferId;
        private String jobOfferTitle;
        private String description;
        private TechSpecializationsEnum techSpecialization;
        private Employer employer;
        private WorkTypesEnum workType;
        private CitiesEnum city;
        private ExperiencesEnum experience;
        private SalaryEnum salary;
        private List<SkillsEnum> mustHaveSkills;
        private List<SkillsEnum> niceToHaveSkills;

        public JobOfferBuilder jobOfferId(Integer jobOfferId) {
            this.jobOfferId = jobOfferId;
            return this;
        }

        public JobOfferBuilder jobOfferTitle(String jobOfferTitle) {
            this.jobOfferTitle = jobOfferTitle;
            return this;
        }

        public JobOfferBuilder description(String description) {
            this.description = description;
            return this;
        }

        public JobOfferBuilder techSpecialization(TechSpecializationsEnum techSpecialization) {
            this.techSpecialization = techSpecialization;
            return this;
        }

        public JobOfferBuilder employer(Employer employer) {
            this.employer = employer;
            return this;
        }

        public JobOfferBuilder workType(WorkTypesEnum workType) {
            this.workType = workType;
            return this;
        }

        public JobOfferBuilder city(CitiesEnum city) {
            this.city = city;
            return this;
        }

        public JobOfferBuilder experience(ExperiencesEnum experience) {
            this.experience = experience;
            return this;
        }

        public JobOfferBuilder salary(SalaryEnum salary) {
            this.salary = salary;
            return this;
        }

        public JobOfferBuilder mustHaveSkills(List<SkillsEnum> mustHaveSkills) {
            this.mustHaveSkills = mustHaveSkills;
            return this;
        }

        public JobOfferBuilder niceToHaveSkills(List<SkillsEnum> niceToHaveSkills) {
            this.niceToHaveSkills = niceToHaveSkills;
            return this;
        }

        public JobOffer build() {
            return new JobOffer(this);
        }
    }
}
