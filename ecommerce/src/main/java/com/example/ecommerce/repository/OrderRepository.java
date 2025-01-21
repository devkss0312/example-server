package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 추가적인 쿼리 메소드가 필요하면 여기에 정의
}

