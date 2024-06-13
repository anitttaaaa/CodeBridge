package pl.zajavka.CodeBridge.infrastructure.database.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "salaryId")
@ToString(of = {"salaryId", "minValue", "maxValue"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salary")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer salaryId;

    @Column(name = "type")
    private String type;

    @Column(name = "city")
    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private Set<JobOfferEntity> jobOffer;



}
