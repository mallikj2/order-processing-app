package com.example.orderprocessingapp;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String customerName, String product, int quantity) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setStatus("CREATED");
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
