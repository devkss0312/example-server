package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // 주문 생성 (POST)
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // 고객 존재 여부 확인
        Long customerId = order.getCustomer().getId();
        return customerRepository.findById(customerId)
                .map(customer -> {
                    order.setCustomer(customer);
                    Order created = orderRepository.save(order);
                    return ResponseEntity
                            .status(HttpStatus.CREATED) // 201 Created
                            .body(created);
                })
                .orElse(ResponseEntity
                        .status(HttpStatus.BAD_REQUEST) // 400 Bad Request
                        .build());
    }

    // 모든 주문 조회 (GET)
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> list = orderRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK) // 200 OK
                .body(list);
    }

    // 주문 ID로 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> ResponseEntity
                        .status(HttpStatus.OK) // 200 OK
                        .body(order))
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND) // 404 Not Found
                        .build());
    }
}
