//package pl.zajavka.CodeBridge.infrastructure.database.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.Set;
//
//@Getter
//@Setter
//@EqualsAndHashCode(of = "skillId")
//@ToString(of = {"skillId", "name"})
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "skill")
//public class SkillEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "skill_id")
//    private Integer skillId;
//
//    @Column(name = "name")
//    private String name;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "skill")
//    private Set<JobOfferSkillEntity> jobOfferSkills;
//
//
//}
