package com.dropbaba.backend.orderservice.service.impl;

import com.dropbaba.backend.orderservice.dto.OrderRequest;
import com.dropbaba.backend.orderservice.messaging.OrderEventPublisher;
import com.dropbaba.backend.orderservice.model.Order;
import com.dropbaba.backend.orderservice.model.OrderItem;
import com.dropbaba.backend.orderservice.model.OrderStatus;
import com.dropbaba.backend.orderservice.repository.OrderRepository;
import com.dropbaba.backend.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public OrderServiceImpl(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Override
    public Order placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setVendorId(request.getVendorId());
        order.setStatus(OrderStatus.PLACED);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderRequest.ItemRequest itemRequest : request.getItems()) {
            OrderItem item = new OrderItem();
            item.setProductName(itemRequest.getProductName());
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(itemRequest.getPrice());
            item.setOrder(order);
            items.add(item);

            total = total.add(itemRequest.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
        }

        order.setItems(items);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        orderEventPublisher.publishOrderPlaced(savedOrder);

        return savedOrder;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public Order updateOrderStatus(Long id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByVendor(Long id) {
        return orderRepository.findByVendorId(id);
    }
}
