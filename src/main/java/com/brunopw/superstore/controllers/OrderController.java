package com.brunopw.superstore.controllers;

import com.brunopw.superstore.Order;
import com.brunopw.superstore.OrderNotFoundException;
import com.brunopw.superstore.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    private List<Order> findAll() {
        return orderService.findAll();
    }

    @PostMapping
    private Order newOrder(@RequestBody Order newOrder) {
        return orderService.save(newOrder);
    }

    @GetMapping("/{id}")
    private Order findById(@PathVariable String id) {
        return orderService.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PutMapping("/{id}")
    private Order replaceOrder(@RequestBody Order newOrder, @PathVariable String id) {

        return orderService.findById(id)
                .map(order -> {
                    order.setBuyer(newOrder.getBuyer());
                    order.setSeller(newOrder.getSeller());
                    order.setDiscount(newOrder.getDiscount());
                    order.setDate(newOrder.getDate());
                    order.setItens(newOrder.getItens());
                    return orderService.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setId(id);
                    return orderService.save(newOrder);
                });
    }

    @DeleteMapping("/{id}")
    private void deleteOrder(@PathVariable String id) {
        orderService.deleteById(id);
    }
}
