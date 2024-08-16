package io.github.reconsolidated.velmorobackend.application;

import io.github.reconsolidated.velmorobackend.domain.order.Order;
import io.github.reconsolidated.velmorobackend.domain.order.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order) {
        order.setDateCreated(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        return orderRepository.findById(savedOrder.getId()).orElseThrow();
    }

    // Read
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Update
    @Transactional
    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderEntries(updatedOrder.getOrderEntries());
            order.setEmailSentDate(updatedOrder.getEmailSentDate());
            order.setEmailReadDate(updatedOrder.getEmailReadDate());
            return orderRepository.save(order);
        });
    }

    // Delete
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
