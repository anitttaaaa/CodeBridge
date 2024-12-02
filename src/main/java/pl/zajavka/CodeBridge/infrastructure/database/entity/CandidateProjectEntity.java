package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = "candidateProjectId")
@ToString(of = {"candidateProjectId", "projectTitle"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate_project")
public class CandidateProjectEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_project_id")
    private Integer candidateProjectId;

    @Column(name = "project_title")
    private String projectTitle;

    @Column(name = "technologies")
    private String technologies;

    @Column(name = "description")
    private String description;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "project_link")
    private String projectLink;

    @Column(name = "candidate_id")
    private Integer candidateId;

}