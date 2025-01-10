package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

@Getter
@Setter
@EqualsAndHashCode(of = "applicationId")
@ToString(of = {"applicationId", "jobOfferId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_application")
public class JobApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "job_offer_id", nullable = false)
    private Integer jobOfferId;

    @Column(name = "employer_id", nullable = false)
    private Integer employerId;

    @Column(name = "candidate_id", nullable = false)
    private Integer candidateId;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status", nullable = false)
    private ApplicationStatus applicationStatus;

}
