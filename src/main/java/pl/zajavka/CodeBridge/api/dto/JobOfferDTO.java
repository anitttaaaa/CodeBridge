package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.*;
import pl.zajavka.CodeBridge.domain.Employer;

import java.util.List;
import java.util.Objects;

public class JobOfferDTO {

    private final Integer jobOfferId;
    private final String jobOfferTitle;
    private final String description;
    private final TechSpecializationsEnum techSpecialization;
    private final WorkTypesEnum workType;
    private final CitiesEnum city;
    private final ExperiencesEnum experience;
    private final SalaryEnum salary;
    private final List<String> mustHaveSkills;
    private final List<String> niceToHaveSkills;
    private final Employer employer;

    private JobOfferDTO(Builder builder) {
        this.jobOfferId = builder.jobOfferId;
        this.jobOfferTitle = builder.jobOfferTitle;
        this.description = builder.description;
        this.techSpecialization = builder.techSpecialization;
        this.workType = builder.workType;
        this.city = builder.city;
        this.experience = builder.experience;
        this.salary = builder.salary;
        this.mustHaveSkills = builder.mustHaveSkills;
        this.niceToHaveSkills = builder.niceToHaveSkills;
        this.employer = builder.employer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOfferDTO that = (JobOfferDTO) o;
        return Objects.equals(jobOfferId, that.jobOfferId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobOfferId);
    }

    @Override
    public String toString() {
        return "JobOfferDTO{" +
                "jobOfferId=" + jobOfferId +
                ", jobOfferTitle='" + jobOfferTitle + '\'' +
                ", description='" + description + '\'' +
                ", techSpecialization=" + techSpecialization +
                ", workType='" + workType + '\'' +
                ", city='" + city + '\'' +
                ", experience='" + experience + '\'' +
                ", salary=" + salary +
                ", mustHaveSkills=" + mustHaveSkills +
                ", niceToHaveSkills=" + niceToHaveSkills +
                ", employer=" + employer +
                '}';
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

    public List<String> getMustHaveSkills() {
        return mustHaveSkills;
    }

    public List<String> getNiceToHaveSkills() {
        return niceToHaveSkills;
    }

    public Employer getEmployer() {
        return employer;
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
        private List<String> mustHaveSkills;
        private List<String> niceToHaveSkills;
        private Employer employer;

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

        public Builder mustHaveSkills(List<String> mustHaveSkills) {
            this.mustHaveSkills = mustHaveSkills;
            return this;
        }

        public Builder niceToHaveSkills(List<String> niceToHaveSkills) {
            this.niceToHaveSkills = niceToHaveSkills;
            return this;
        }

        public Builder employer(Employer employer) {
            this.employer = employer;
            return this;
        }

        public JobOfferDTO build() {
            return new JobOfferDTO(this);
        }
    }
}
