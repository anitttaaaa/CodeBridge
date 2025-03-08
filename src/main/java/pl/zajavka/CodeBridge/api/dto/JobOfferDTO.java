package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.domain.Employer;

import java.util.List;


public class JobOfferDTO {

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

    public JobOfferDTO() {
    }

    public JobOfferDTO(Integer jobOfferId, String jobOfferTitle, String description,
                       String techSpecialization, String workType, String city,
                       String experience, String salary, List<String> mustHaveSkills,
                       List<String> niceToHaveSkills) {
        this.jobOfferId = jobOfferId;
        this.jobOfferTitle = jobOfferTitle;
        this.description = description;
        this.techSpecialization = techSpecialization;
        this.workType = workType;
        this.city = city;
        this.experience = experience;
        this.salary = salary;
        this.mustHaveSkills = mustHaveSkills;
        this.niceToHaveSkills = niceToHaveSkills;
    }

    public JobOfferDTO(Integer jobOfferId, String jobOfferTitle, String description,
                       String techSpecialization, String workType, String city,
                       String experience, String salary, List<String> mustHaveSkills,
                       List<String> niceToHaveSkills, Employer employer) {
        this.jobOfferId = jobOfferId;
        this.jobOfferTitle = jobOfferTitle;
        this.description = description;
        this.techSpecialization = techSpecialization;
        this.workType = workType;
        this.city = city;
        this.experience = experience;
        this.salary = salary;
        this.mustHaveSkills = mustHaveSkills;
        this.niceToHaveSkills = niceToHaveSkills;
        this.employer = employer;
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
}








