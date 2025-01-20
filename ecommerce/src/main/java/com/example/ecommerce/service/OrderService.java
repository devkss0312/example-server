package com.example.ecommerce.service;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Create
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Read All
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Read by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Update
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found for id :: " + id));
        order.setOrderDate(orderDetails.getOrderDate());
        order.setCustomer(orderDetails.getCustomer());
        return orderRepository.save(order);
    }

    // Delete
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found for id :: " + id));
        orderRepository.delete(order);
    }
}
