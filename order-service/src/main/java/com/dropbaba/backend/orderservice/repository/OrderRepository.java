package com.dropbaba.backend.orderservice.repository;

import com.dropbaba.backend.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByVendorId(Long id);
}
