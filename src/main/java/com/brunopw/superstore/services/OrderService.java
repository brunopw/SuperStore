package com.brunopw.superstore.services;

import com.brunopw.superstore.Order;
import com.brunopw.superstore.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order newOrder) {
        newOrder.setDiscount(calculateDiscount(newOrder));
        return orderRepository.save(newOrder);
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    private Double calculateDiscount(Order order) {
        /*Discount system:
            Clients over 70 years old, 15% discount
            Clients over 50 years old, 10% discount
            Clients living in Dublin, 7% discount
            The discounts are not cumulative!*/

        if(order.getBuyer().getBirthday().isBefore(LocalDate.now().minusYears(70))) {
            return 15.0;
        } else if(order.getBuyer().getBirthday().isBefore(LocalDate.now().minusYears(50))) {
            return 10.0;
        } else if(order.getBuyer().getAddress().equalsIgnoreCase("Dublin")) {
            return 7.0;
        }
        return 0.0;
    }
}
