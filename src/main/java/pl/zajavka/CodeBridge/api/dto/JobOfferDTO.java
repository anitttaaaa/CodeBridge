package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.*;
import pl.zajavka.CodeBridge.domain.Employer;

import java.util.List;

public class JobOfferDTO {

    private final Integer jobOfferId;
    private final String jobOfferTitle;
    private final String description;
    private final TechSpecializationsEnum techSpecialization;
    private final WorkTypesEnum workType;
    private final CitiesEnum city;
    private final ExperiencesEnum experience;
    private final SalaryEnum salary; // Zmieniono z String na SalaryEnum
    private final List<String> mustHaveSkills;
    private final List<String> niceToHaveSkills;
    private final Employer employer;

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

    public JobOfferDTO(Integer jobOfferId, String jobOfferTitle, String description, TechSpecializationsEnum techSpecialization,
                       WorkTypesEnum workType, CitiesEnum city, ExperiencesEnum experience, SalaryEnum salary,
                       List<String> mustHaveSkills, List<String> niceToHaveSkills, Employer employer) {
        this.jobOfferId = jobOfferId;
        this.jobOfferTitle = jobOfferTitle;
        this.description = description;
        this.techSpecialization = techSpecialization;
        this.workType = workType;
        this.city = city;
        this.experience = experience;
        this.salary = salary; // Przypisanie wartości SalaryEnum
        this.mustHaveSkills = mustHaveSkills;
        this.niceToHaveSkills = niceToHaveSkills;
        this.employer = employer;
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
}
