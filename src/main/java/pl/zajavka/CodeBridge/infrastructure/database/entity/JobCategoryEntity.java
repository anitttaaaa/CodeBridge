//package pl.zajavka.CodeBridge.infrastructure.database.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Getter
//@Setter
//@EqualsAndHashCode(of = "jobCategoryId")
//@ToString(of = {"jobCategoryId", "name"})
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "job_category")
//public class JobCategoryEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "job_category_id")
//    private Integer jobCategoryId;
//
//    @Column(name = "name")
//    private String name;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobCategory", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<JobOfferCategoryEntity> jobOfferCategories = new ArrayList<>();
//
//
//}

