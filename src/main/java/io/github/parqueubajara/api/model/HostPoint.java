package io.github.parqueubajara.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_host_point")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HostPoint extends TouristSpot{

    @Column(name = "num_of_rooms")
    private Integer numOfRooms;

    @Column(name = "avg_price")
    private BigDecimal avgPrice;

    @Column(name = "booking_adress")
    private String bookingAdress;
}
