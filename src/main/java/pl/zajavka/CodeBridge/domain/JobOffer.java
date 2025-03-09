package pl.zajavka.CodeBridge.domain;

import java.util.List;
import java.util.Objects;

public class JobOffer {

    private final Integer jobOfferId;
    private final String jobOfferTitle;
    private final String description;
    private final String techSpecialization;
    private final Employer employer;
    private final String workType;
    private final String city;
    private final String experience;
    private final String salary;
    private final List<String> mustHaveSkills;
    private final List<String> niceToHaveSkills;

    // Prywatny konstruktor, który będzie używany przez Buildera
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

    // Gettery
    public Integer getJobOfferId() {
        return jobOfferId;
    }

    public String getJobOfferTitle() {
        return jobOfferTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getTechSpecialization() {
        return techSpecialization;
    }

    public Employer getEmployer() {
        return employer;
    }

    public String getWorkType() {
        return workType;
    }

    public String getCity() {
        return city;
    }

    public String getExperience() {
        return experience;
    }

    public String getSalary() {
        return salary;
    }

    public List<String> getMustHaveSkills() {
        return mustHaveSkills;
    }

    public List<String> getNiceToHaveSkills() {
        return niceToHaveSkills;
    }

    // Klasa Builder
    public static class JobOfferBuilder {

        private Integer jobOfferId;
        private String jobOfferTitle;
        private String description;
        private String techSpecialization;
        private Employer employer;
        private String workType;
        private String city;
        private String experience;
        private String salary;
        private List<String> mustHaveSkills;
        private List<String> niceToHaveSkills;

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

        public JobOfferBuilder techSpecialization(String techSpecialization) {
            this.techSpecialization = techSpecialization;
            return this;
        }

        public JobOfferBuilder employer(Employer employer) {
            this.employer = employer;
            return this;
        }

        public JobOfferBuilder workType(String workType) {
            this.workType = workType;
            return this;
        }

        public JobOfferBuilder city(String city) {
            this.city = city;
            return this;
        }

        public JobOfferBuilder experience(String experience) {
            this.experience = experience;
            return this;
        }

        public JobOfferBuilder salary(String salary) {
            this.salary = salary;
            return this;
        }

        public JobOfferBuilder mustHaveSkills(List<String> mustHaveSkills) {
            this.mustHaveSkills = mustHaveSkills;
            return this;
        }

        public JobOfferBuilder niceToHaveSkills(List<String> niceToHaveSkills) {
            this.niceToHaveSkills = niceToHaveSkills;
            return this;
        }

        public JobOffer build() {
            return new JobOffer(this);
        }
    }

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
                ", techSpecialization='" + techSpecialization + '\'' +
                ", employer=" + employer +
                ", workType='" + workType + '\'' +
                ", city='" + city + '\'' +
                ", experience='" + experience + '\'' +
                ", salary='" + salary + '\'' +
                ", mustHaveSkills=" + mustHaveSkills +
                ", niceToHaveSkills=" + niceToHaveSkills +
                '}';
    }
}
