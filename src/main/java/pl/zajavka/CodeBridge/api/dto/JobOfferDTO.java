package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.domain.Employer;

import java.util.List;

public class JobOfferDTO {

    private final Integer jobOfferId;
    private final String jobOfferTitle;
    private final String description;
    private final String techSpecialization;
    private final String workType;
    private final String city;
    private final String experience;
    private final String salary;
    private final List<String> mustHaveSkills;
    private final List<String> niceToHaveSkills;
    private final Employer employer;

    private JobOfferDTO(JobOfferDTOBuilder builder) {
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

    public Employer getEmployer() {
        return employer;
    }

    public static class JobOfferDTOBuilder {

        private Integer jobOfferId;
        private String jobOfferTitle;
        private String description;
        private String techSpecialization;
        private String workType;
        private String city;
        private String experience;
        private String salary;
        private List<String> mustHaveSkills;
        private List<String> niceToHaveSkills;
        private Employer employer;

        public JobOfferDTOBuilder jobOfferId(Integer jobOfferId) {
            this.jobOfferId = jobOfferId;
            return this;
        }

        public JobOfferDTOBuilder jobOfferTitle(String jobOfferTitle) {
            this.jobOfferTitle = jobOfferTitle;
            return this;
        }

        public JobOfferDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public JobOfferDTOBuilder techSpecialization(String techSpecialization) {
            this.techSpecialization = techSpecialization;
            return this;
        }

        public JobOfferDTOBuilder workType(String workType) {
            this.workType = workType;
            return this;
        }

        public JobOfferDTOBuilder city(String city) {
            this.city = city;
            return this;
        }

        public JobOfferDTOBuilder experience(String experience) {
            this.experience = experience;
            return this;
        }

        public JobOfferDTOBuilder salary(String salary) {
            this.salary = salary;
            return this;
        }

        public JobOfferDTOBuilder mustHaveSkills(List<String> mustHaveSkills) {
            this.mustHaveSkills = mustHaveSkills;
            return this;
        }

        public JobOfferDTOBuilder niceToHaveSkills(List<String> niceToHaveSkills) {
            this.niceToHaveSkills = niceToHaveSkills;
            return this;
        }

        public JobOfferDTOBuilder employer(Employer employer) {
            this.employer = employer;
            return this;
        }

        public JobOfferDTO build() {
            return new JobOfferDTO(this);
        }
    }

}
