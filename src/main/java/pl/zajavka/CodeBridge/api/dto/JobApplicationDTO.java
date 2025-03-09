package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

public class JobApplicationDTO {

    private final Integer applicationId;
    private final JobOfferDTO jobOffer;
    private final EmployerDTO employer;
    private final CandidateDTO candidate;
    private final ApplicationStatus applicationStatus;

    // Prywatny konstruktor używany przez builder
    private JobApplicationDTO(JobApplicationDTOBuilder builder) {
        this.applicationId = builder.applicationId;
        this.jobOffer = builder.jobOffer;
        this.employer = builder.employer;
        this.candidate = builder.candidate;
        this.applicationStatus = builder.applicationStatus;
    }

    // Gettery
    public Integer getApplicationId() {
        return applicationId;
    }

    public JobOfferDTO getJobOffer() {
        return jobOffer;
    }

    public EmployerDTO getEmployer() {
        return employer;
    }

    public CandidateDTO getCandidate() {
        return candidate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    @Override
    public String toString() {
        return "JobApplicationDTO{" +
                "applicationId=" + applicationId +
                ", jobOffer=" + jobOffer +
                ", employer=" + employer +
                ", candidate=" + candidate +
                ", applicationStatus=" + applicationStatus +
                '}';
    }

    // Klasa Builder
    public static class JobApplicationDTOBuilder {

        private Integer applicationId;
        private JobOfferDTO jobOffer;
        private EmployerDTO employer;
        private CandidateDTO candidate;
        private ApplicationStatus applicationStatus;

        // Settery w Builderze
        public JobApplicationDTOBuilder applicationId(Integer applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public JobApplicationDTOBuilder jobOffer(JobOfferDTO jobOffer) {
            this.jobOffer = jobOffer;
            return this;
        }

        public JobApplicationDTOBuilder employer(EmployerDTO employer) {
            this.employer = employer;
            return this;
        }

        public JobApplicationDTOBuilder candidate(CandidateDTO candidate) {
            this.candidate = candidate;
            return this;
        }

        public JobApplicationDTOBuilder applicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
            return this;
        }

        // Metoda build, która zwraca gotowy obiekt JobApplicationDTO
        public JobApplicationDTO build() {
            return new JobApplicationDTO(this);
        }
    }
}
