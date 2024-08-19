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

    public double getTotal() {
        double total = 0.0;
        for (OrderEntry entry : orderEntries) {
            total += entry.getTotalPrice();
        }
        return total;
    }

    public String prettyPrintOrderEntries() {
        StringBuilder sb = new StringBuilder();

        sb.append("<table style='width: 100%; border-collapse: collapse;'>");
        sb.append("<tr>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Nazwa</th>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Ilość</th>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Cena za sztukę</th>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Łączna Cena</th>")
                .append("</tr>");

        for (OrderEntry entry : orderEntries) {
            sb.append("<tr>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(entry.getItem().getDisplayName()).append("</td>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px; text-align: center;'>").append(entry.getQuantity()).append("</td>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px; text-align: right;'>").append(String.format("%.2f zł", entry.getItem().getPrice())).append("</td>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px; text-align: right;'>").append(String.format("%.2f zł", entry.getTotalPrice())).append("</td>")
                    .append("</tr>");
        }

        sb.append("</table>");
        return sb.toString();
    }
}


