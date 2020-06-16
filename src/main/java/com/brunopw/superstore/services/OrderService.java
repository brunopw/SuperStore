package com.brunopw.superstore.services;

import com.brunopw.superstore.Order;
import com.brunopw.superstore.User;
import com.brunopw.superstore.calcs.DiscountRuleResolver;
import com.brunopw.superstore.repositories.OrderRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public void calculateTotalPrice(Order order) {
        order.setDiscount(DiscountRuleResolver.getInstance().calculateDicount(order));
        if(!order.getItens().isEmpty()) {
            double totalItensPrice = getTotalItensPrice(order);
            double totalPrice = calculatePercentage(order.getDiscount(), totalItensPrice);
            BigDecimal totalPriceBd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);

            order.setTotalPrice(totalPriceBd.doubleValue());
        } else {
            order.setTotalPrice(0d);
        }
    }

    private double calculatePercentage(double discount, double totalPrice) {
        return totalPrice - ((discount / 100) * totalPrice);
    }

    private double getTotalItensPrice(Order order) {
        return order.getItens().stream().mapToDouble(i -> (i.getPrice() * i.getQuantity())).sum();
    }

    public List<Order> findByBuyerNameContaining(String buyerName) {
        return orderRepository.findByBuyerNameContaining(buyerName);
    }

}
