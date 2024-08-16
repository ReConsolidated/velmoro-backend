package io.github.reconsolidated.velmorobackend.domain.order;

import io.github.reconsolidated.velmorobackend.domain.hotel.Hotel;
import io.github.reconsolidated.velmorobackend.domain.orderEntry.OrderEntry;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "velmoro_order")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Hotel hotel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderEntry> orderEntries;

    private LocalDateTime dateCreated;
    private LocalDateTime emailSentDate;
    private LocalDateTime emailReadDate;
    private String roomNumber;
    private String name;
}


