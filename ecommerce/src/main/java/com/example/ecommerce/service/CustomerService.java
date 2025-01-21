package com.example.ecommerce.service;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Read All
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Read by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Update
    @Transactional
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found for id :: " + id));
        customer.setName(customerDetails.getName());
        return customerRepository.save(customer);
    }

    // Delete
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found for id :: " + id));
        customerRepository.delete(customer);
    }
}
