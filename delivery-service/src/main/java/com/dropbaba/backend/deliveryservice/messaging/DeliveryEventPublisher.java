package com.dropbaba.backend.deliveryservice.messaging;

import com.dropbaba.backend.deliveryservice.event.DeliveryStatusChangedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.delivery.status.exchange}")
    private String exchange;

    @Value("${rabbitmq.delivery.status.routing-key}")
    private String routingKey;

    public DeliveryEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishDeliveryStatusChangedEvent(DeliveryStatusChangedEvent event) {
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}
