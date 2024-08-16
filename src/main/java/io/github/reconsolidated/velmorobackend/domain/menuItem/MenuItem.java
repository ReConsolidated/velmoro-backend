package io.github.reconsolidated.velmorobackend.domain.menuItem;

import io.github.reconsolidated.velmorobackend.domain.hotel.Hotel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Hotel hotel;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "priority", nullable = false, columnDefinition = "INTEGER DEFAULT 100")
    private Integer priority;
}