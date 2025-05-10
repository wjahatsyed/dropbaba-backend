package com.dropbaba.backend.orderservice.service;

import com.dropbaba.backend.orderservice.dto.OrderRequest;
import com.dropbaba.backend.orderservice.model.Order;
import com.dropbaba.backend.orderservice.model.OrderStatus;

import java.util.List;

public interface OrderService {
    Order placeOrder(OrderRequest request);

    Order getOrderById(Long id);

    Order updateOrderStatus(Long id, OrderStatus newStatus);

    List<Order> getOrdersByVendor(Long id);
}
