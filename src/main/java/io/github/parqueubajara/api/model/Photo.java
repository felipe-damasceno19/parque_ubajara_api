package io.github.parqueubajara.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_photo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "touristSpot")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Photo extends BaseEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "storage_key")
    private String storageKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourist_spot_id")
    private TouristSpot touristSpot;

}