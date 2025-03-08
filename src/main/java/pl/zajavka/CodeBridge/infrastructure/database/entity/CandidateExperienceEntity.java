package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Builder

@Entity
@Table(name = "candidate_experience")
public class CandidateExperienceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_experience_id")
    private Integer candidateExperienceId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "candidate_position")
    private String candidatePosition;

    @Column(name = "description")
    private String description;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "candidate_id")
    private Integer candidateId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateExperienceEntity that = (CandidateExperienceEntity) o;
        return Objects.equals(candidateExperienceId, that.candidateExperienceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateExperienceId);
    }

    @Override
    public String toString() {
        return "CandidateExperienceEntity{" +
                "candidateExperienceId=" + candidateExperienceId +
                ", companyName='" + companyName + '\'' +
                ", candidatePosition='" + candidatePosition + '\'' +
                ", description='" + description + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
    }

    public CandidateExperienceEntity() {
    }

    public CandidateExperienceEntity(Integer candidateExperienceId, String companyName,
                                     String candidatePosition, String description,
                                     LocalDate fromDate, LocalDate toDate, Integer candidateId) {
        this.candidateExperienceId = candidateExperienceId;
        this.companyName = companyName;
        this.candidatePosition = candidatePosition;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.candidateId = candidateId;
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
