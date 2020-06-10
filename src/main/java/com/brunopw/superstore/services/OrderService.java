package com.brunopw.superstore.services;

import com.brunopw.superstore.Order;
import com.brunopw.superstore.repositories.OrderRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        calculateTotalPrice(newOrder);
        return orderRepository.save(newOrder);
    }

    public Order saveOrUpdate(Order newOrder) {
        calculateTotalPrice(newOrder);
        Example orderExample = Example.of(new Order(newOrder.getBuyer(), newOrder.getSeller(), newOrder.getDate()));
        List<Order> orders = orderRepository.findAll(orderExample);
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


    public double calculatePercentage(double discount, double totalPrice) {
        return totalPrice - ((discount / 100) * totalPrice);
    }

    public void calculateTotalPrice(Order order) {
        order.setDiscount(calculateDiscount(order));
        if(!order.getItens().isEmpty()) {
            double totalItensPrice = order.getItens().stream().mapToDouble(i -> (i.getPrice() * i.getQuantity())).sum();
            double totalPrice = calculatePercentage(order.getDiscount(), totalItensPrice);
            BigDecimal totalPriceBd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);

            order.setTotalPrice(totalPriceBd.doubleValue());
        } else {
            order.setTotalPrice(0d);
        }
    }
}
