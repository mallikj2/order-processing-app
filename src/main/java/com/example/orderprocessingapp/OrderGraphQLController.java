package com.example.orderprocessingapp;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderGraphQLController {

    private final OrderService orderService;

    public OrderGraphQLController(OrderService orderService) {
        this.orderService = orderService;
    }

    @QueryMapping
    public List<Order> orders() {
        return orderService.getOrders();
    }

    @QueryMapping
    public Order order(@Argument Long id) {
        return orderService.getOrderById(id);
    }

    @MutationMapping
    public Order createOrder(@Argument String customerName, @Argument String product, @Argument int quantity) {
        return orderService.createOrder(customerName, product, quantity);
    }
}
