package io.github.reconsolidated.velmorobackend.domain.category;

import io.github.reconsolidated.velmorobackend.domain.hotel.Hotel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String nameEN;
    private String nameDE;
    private String svgUrl;
    @Column(name = "priority", nullable = false, columnDefinition = "INTEGER DEFAULT 100")
    private Integer priority;
    @ManyToOne
    private Hotel hotel;
}