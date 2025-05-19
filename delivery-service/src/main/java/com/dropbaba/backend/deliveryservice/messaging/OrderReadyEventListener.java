package com.dropbaba.backend.deliveryservice.messaging;

import com.dropbaba.backend.deliveryservice.event.OrderReadyForDeliveryEvent;
import com.dropbaba.backend.deliveryservice.service.DeliveryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderReadyEventListener {

    private final DeliveryService deliveryService;

    public OrderReadyEventListener(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @RabbitListener(queues = "${rabbitmq.order.ready.queue}")
    public void handleOrderReadyEvent(OrderReadyForDeliveryEvent event) {
        deliveryService.createDelivery(event);
    }
}
