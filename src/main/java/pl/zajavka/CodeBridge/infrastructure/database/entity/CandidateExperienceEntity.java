package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = "candidateExperienceId")
@ToString(of = {"candidateExperienceId", "companyName","candidatePosition"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
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


}
