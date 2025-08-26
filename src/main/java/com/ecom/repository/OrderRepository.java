package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    // You can add custom queries here if needed
}

