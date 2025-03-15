package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateExperienceDTO {
    private final Integer candidateExperienceId;
    private final String companyName;
    private final String candidatePosition;
    private final String description;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Integer candidateId;

    private CandidateExperienceDTO(Builder builder) {
        this.candidateExperienceId = builder.candidateExperienceId;
        this.companyName = builder.companyName;
        this.candidatePosition = builder.candidatePosition;
        this.description = builder.description;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.candidateId = builder.candidateId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer candidateExperienceId;
        private String companyName;
        private String candidatePosition;
        private String description;
        private LocalDate fromDate;
        private LocalDate toDate;
        private Integer candidateId;

        public Builder candidateExperienceId(Integer candidateExperienceId) {
            this.candidateExperienceId = candidateExperienceId;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder candidatePosition(String candidatePosition) {
            this.candidatePosition = candidatePosition;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public CandidateExperienceDTO build() {
            return new CandidateExperienceDTO(this);
        }
    }

    @Override
    public String toString() {
        return "CandidateExperienceDTO{" +
                "candidateExperienceId=" + candidateExperienceId +
                ", companyName='" + companyName + '\'' +
                ", candidatePosition='" + candidatePosition + '\'' +
                ", description='" + description + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
    }

    public Integer getCandidateExperienceId() {
        return candidateExperienceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCandidatePosition() {
        return candidatePosition;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Integer getCandidateId() {
        return candidateId;
    }
}