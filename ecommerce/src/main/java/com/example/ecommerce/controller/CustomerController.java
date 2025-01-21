package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // 고객 생성 (POST)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer created = customerRepository.save(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 Created
                .body(created);
    }

    // 모든 고객 조회 (GET)
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> list = customerRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK) // 200 OK
                .body(list);
    }

    // 고객 ID로 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> ResponseEntity
                        .status(HttpStatus.OK) // 200 OK
                        .body(customer))
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND) // 404 Not Found
                        .build());
    }
}
