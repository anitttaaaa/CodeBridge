package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.*;

import java.util.List;

public class JobApplicationDTO {

    private final Integer applicationId;

    private final String companyName;

    private final String candidateName;
    private final String candidateSurname;
    private final String candidateEmail;
    private final String candidatePhone;
    private final TechSpecializationsEnum candidateTechSpecialization;
    private final List<SkillsEnum> candidateSkills;

    private final Integer jobOfferId;
    private final String jobOfferTitle;
    private final String jobOfferDescription;
    private final TechSpecializationsEnum jobOfferTechSpecialization;
    private final WorkTypesEnum jobOfferWorkType;
    private final CitiesEnum jobOfferCity;
    private final ExperiencesEnum jobOfferExperience;
    private final SalaryEnum jobOfferSalary;
    private final List<SkillsEnum> jobOfferMustHaveSkills;
    private final List<SkillsEnum> jobOfferNiceToHaveSkills;

    private final ApplicationStatusEnum applicationStatusEnum;

    private JobApplicationDTO(Builder builder) {
        this.applicationId = builder.applicationId;
        this.jobOfferId = builder.jobOfferId;
        this.jobOfferTitle = builder.jobOfferTitle;
        this.jobOfferDescription = builder.jobOfferDescription;
        this.jobOfferTechSpecialization = builder.jobOfferTechSpecialization;
        this.jobOfferWorkType = builder.jobOfferWorkType;
        this.jobOfferCity = builder.jobOfferCity;
        this.jobOfferExperience = builder.jobOfferExperience;
        this.jobOfferSalary = builder.jobOfferSalary;
        this.jobOfferMustHaveSkills = builder.jobOfferMustHaveSkills;
        this.jobOfferNiceToHaveSkills = builder.jobOfferNiceToHaveSkills;
        this.companyName = builder.companyName;
        this.candidateName = builder.candidateName;
        this.candidateSurname = builder.candidateSurname;
        this.candidateEmail = builder.candidateEmail;
        this.candidatePhone = builder.candidatePhone;
        this.candidateTechSpecialization = builder.candidateTechSpecialization;
        this.candidateSkills = builder.candidateSkills;
        this.applicationStatusEnum = builder.applicationStatusEnum;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public Integer getJobOfferId() {
        return jobOfferId;
    }

    public String getJobOfferTitle() {
        return jobOfferTitle;
    }

    public String getJobOfferDescription() {
        return jobOfferDescription;
    }

    public TechSpecializationsEnum getJobOfferTechSpecialization() {
        return jobOfferTechSpecialization;
    }

    public WorkTypesEnum getJobOfferWorkType() {
        return jobOfferWorkType;
    }

    public CitiesEnum getJobOfferCity() {
        return jobOfferCity;
    }

    public ExperiencesEnum getJobOfferExperience() {
        return jobOfferExperience;
    }

    public SalaryEnum getJobOfferSalary() {
        return jobOfferSalary;
    }

    public List<SkillsEnum> getJobOfferMustHaveSkills() {
        return jobOfferMustHaveSkills;
    }

    public List<SkillsEnum> getJobOfferNiceToHaveSkills() {
        return jobOfferNiceToHaveSkills;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getCandidateSurname() {
        return candidateSurname;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public String getCandidatePhone() {
        return candidatePhone;
    }

    public TechSpecializationsEnum getCandidateTechSpecialization() {
        return candidateTechSpecialization;
    }

    public List<SkillsEnum> getCandidateSkills() {
        return candidateSkills;
    }

    public ApplicationStatusEnum getApplicationStatusEnum() {
        return applicationStatusEnum;
    }

    @Override
    public String toString() {
        return "JobApplicationDTO{" +
                "applicationId=" + applicationId +
                ", jobOfferId=" + jobOfferId +
                ", jobOfferTitle='" + jobOfferTitle + '\'' +
                ", jobOfferDescription='" + jobOfferDescription + '\'' +
                ", jobOfferTechSpecialization=" + jobOfferTechSpecialization +
                ", jobOfferWorkType=" + jobOfferWorkType +
                ", jobOfferCity=" + jobOfferCity +
                ", jobOfferExperience=" + jobOfferExperience +
                ", jobOfferSalary=" + jobOfferSalary +
                ", jobOfferMustHaveSkills=" + jobOfferMustHaveSkills +
                ", jobOfferNiceToHaveSkills=" + jobOfferNiceToHaveSkills +
                ", companyName='" + companyName + '\'' +
                ", candidateName='" + candidateName + '\'' +
                ", candidateSurname='" + candidateSurname + '\'' +
                ", candidateEmail='" + candidateEmail + '\'' +
                ", candidatePhone='" + candidatePhone + '\'' +
                ", candidateTechSpecialization=" + candidateTechSpecialization +
                ", candidateSkills=" + candidateSkills +
                ", applicationStatusEnum=" + applicationStatusEnum +
                '}';
    }

    public static class Builder {
        private Integer applicationId;
        private Integer jobOfferId;
        private String jobOfferTitle;
        private String jobOfferDescription;
        private TechSpecializationsEnum jobOfferTechSpecialization;
        private WorkTypesEnum jobOfferWorkType;
        private CitiesEnum jobOfferCity;
        private ExperiencesEnum jobOfferExperience;
        private SalaryEnum jobOfferSalary;
        private List<SkillsEnum> jobOfferMustHaveSkills;
        private List<SkillsEnum> jobOfferNiceToHaveSkills;

        private String companyName;
        private String candidateName;
        private String candidateSurname;
        private String candidateEmail;
        private String candidatePhone;
        private TechSpecializationsEnum candidateTechSpecialization;
        private List<SkillsEnum> candidateSkills;
        private ApplicationStatusEnum applicationStatusEnum;

        public Builder applicationId(Integer applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder jobOfferId(Integer jobOfferId) {
            this.jobOfferId = jobOfferId;
            return this;
        }

        public Builder jobOfferTitle(String jobOfferTitle) {
            this.jobOfferTitle = jobOfferTitle;
            return this;
        }

        public Builder jobOfferDescription(String jobOfferDescription) {
            this.jobOfferDescription = jobOfferDescription;
            return this;
        }

        public Builder jobOfferTechSpecialization(TechSpecializationsEnum jobOfferTechSpecialization) {
            this.jobOfferTechSpecialization = jobOfferTechSpecialization;
            return this;
        }

        public Builder jobOfferWorkType(WorkTypesEnum jobOfferWorkType) {
            this.jobOfferWorkType = jobOfferWorkType;
            return this;
        }

        public Builder jobOfferCity(CitiesEnum jobOfferCity) {
            this.jobOfferCity = jobOfferCity;
            return this;
        }

        public Builder jobOfferExperience(ExperiencesEnum jobOfferExperience) {
            this.jobOfferExperience = jobOfferExperience;
            return this;
        }

        public Builder jobOfferSalary(SalaryEnum jobOfferSalary) {
            this.jobOfferSalary = jobOfferSalary;
            return this;
        }

        public Builder jobOfferMustHaveSkills(List<SkillsEnum> jobOfferMustHaveSkills) {
            this.jobOfferMustHaveSkills = jobOfferMustHaveSkills;
            return this;
        }

        public Builder jobOfferNiceToHaveSkills(List<SkillsEnum> jobOfferNiceToHaveSkills) {
            this.jobOfferNiceToHaveSkills = jobOfferNiceToHaveSkills;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder candidateName(String candidateName) {
            this.candidateName = candidateName;
            return this;
        }

        public Builder candidateSurname(String candidateSurname) {
            this.candidateSurname = candidateSurname;
            return this;
        }

        public Builder candidateEmail(String candidateEmail) {
            this.candidateEmail = candidateEmail;
            return this;
        }

        public Builder candidatePhone(String candidatePhone) {
            this.candidatePhone = candidatePhone;
            return this;
        }

        public Builder candidateTechSpecialization(TechSpecializationsEnum candidateTechSpecialization) {
            this.candidateTechSpecialization = candidateTechSpecialization;
            return this;
        }

        public Builder candidateSkills(List<SkillsEnum> candidateSkills) {
            this.candidateSkills = candidateSkills;
            return this;
        }

        public Builder applicationStatusEnum(ApplicationStatusEnum applicationStatusEnum) {
            this.applicationStatusEnum = applicationStatusEnum;
            return this;
        }

        public JobApplicationDTO build() {
            return new JobApplicationDTO(this);
        }
    }
}
