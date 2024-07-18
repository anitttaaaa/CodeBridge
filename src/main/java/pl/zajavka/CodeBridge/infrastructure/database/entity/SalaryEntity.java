//package pl.zajavka.CodeBridge.infrastructure.database.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.math.BigDecimal;
//import java.util.Set;
//
//@Getter
//@Setter
//@EqualsAndHashCode(of = "salaryId")
//@ToString(of = {"salaryId", "minValue", "maxValue"})
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "salary")
//public class SalaryEntity {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "salary_id")
//    private Integer salaryId;
//
//    @Column(name = "min_value")
//    private BigDecimal minValue;
//
//    @Column(name = "max_value")
//    private BigDecimal maxValue;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salary")
//    private Set<JobOfferEntity> jobOffers;
//
//
//
//}
