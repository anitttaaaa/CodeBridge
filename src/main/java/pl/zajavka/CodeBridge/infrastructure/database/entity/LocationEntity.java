package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "locationId")
@ToString(of = {"locationId", "type", "city"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_location")

public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "type")
    private String type;

    @Column(name = "city")
    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job_location")
    private Set<JobOfferEntity> jobOffers;


}

