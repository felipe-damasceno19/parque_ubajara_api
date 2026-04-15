package io.github.parqueubajara.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_tour_guide")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "photos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class TourGuide {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "languages")
    private List<String> languages;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "tourGuide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    @Column(name = "active")
    private Boolean active;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
