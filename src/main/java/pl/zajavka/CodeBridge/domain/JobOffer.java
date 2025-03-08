package pl.zajavka.CodeBridge.domain;

import java.util.List;
import java.util.Objects;

public class JobOffer {

    Integer jobOfferId;
    String jobOfferTitle;
    String description;
    String techSpecialization;
    Employer employer;
    String workType;
    String city;
    String experience;
    String salary;
    List<String> mustHaveSkills;
    List<String> niceToHaveSkills;

    public JobOffer(Integer jobOfferId) {
        this.jobOfferId = jobOfferId;
    }

    public JobOffer(Integer jobOfferId, String jobOfferTitle, String description,
                    String techSpecialization, String city, String workType,
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

    public JobOffer(Integer jobOfferId, String jobOfferTitle, String description,
                    String techSpecialization, Employer employer, String workType,
                    String city, String experience, String salary, List<String> mustHaveSkills,
                    List<String> niceToHaveSkills) {
        this.jobOfferId = jobOfferId;
        this.jobOfferTitle = jobOfferTitle;
        this.description = description;
        this.techSpecialization = techSpecialization;
        this.employer = employer;
        this.workType = workType;
        this.city = city;
        this.experience = experience;
        this.salary = salary;
        this.mustHaveSkills = mustHaveSkills;
        this.niceToHaveSkills = niceToHaveSkills;
    }

    public JobOffer() {
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
}


