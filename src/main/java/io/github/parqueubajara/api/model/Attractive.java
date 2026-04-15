package io.github.parqueubajara.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_attractive")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Attractive extends TouristSpot {

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "entry_price")
    private BigDecimal entryPrice;

    @Column(name = "has_guide")
    private Boolean hasGuide;

    @Column(name = "average_visit_duration")
    private Integer averageVisitDuration;

}
