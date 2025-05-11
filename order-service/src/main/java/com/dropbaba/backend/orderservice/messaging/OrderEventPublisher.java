package com.dropbaba.backend.orderservice.messaging;

import com.dropbaba.backend.orderservice.event.OrderPlacedEvent;
import com.dropbaba.backend.orderservice.model.Order;
import com.dropbaba.backend.orderservice.model.OrderItem;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.vendor.exchange}")
    private String vendorExchange;

    @Value("${rabbitmq.vendor.routing-key}")
    private String vendorRoutingKey;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderPlaced(Order order) {
        OrderPlacedEvent event = new OrderPlacedEvent();
        event.setOrderId(order.getId());
        event.setUserId(order.getUserId());
        event.setVendorId(order.getVendorId());
        event.setTotalAmount(order.getTotalAmount());

        List<OrderPlacedEvent.Item> items = order.getItems().stream().map(i -> {
            OrderPlacedEvent.Item item = new OrderPlacedEvent.Item();
            item.setProductName(i.getProductName());
            item.setQuantity(i.getQuantity());
            item.setPrice(i.getPrice());
            return item;
        }).collect(Collectors.toList());

        event.setItems(items);

        rabbitTemplate.convertAndSend(vendorExchange, vendorRoutingKey, event);
    }
}
