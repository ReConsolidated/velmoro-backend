package io.github.reconsolidated.velmorobackend.domain.orderEntry;

import io.github.reconsolidated.velmorobackend.domain.menuItem.MenuItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private MenuItem item;
    private Double totalPrice;
    private Integer quantity;
}

