package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 추가적인 쿼리 메소드가 필요하면 여기에 정의
}
