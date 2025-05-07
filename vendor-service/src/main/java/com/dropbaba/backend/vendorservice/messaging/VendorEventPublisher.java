package com.dropbaba.backend.vendorservice.messaging;


import com.dropbaba.backend.vendorservice.event.VendorCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VendorEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.vendor.exchange}")
    private String exchange;

    @Value("${rabbitmq.vendor.routing-key}")
    private String routingKey;

    public VendorEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishVendorCreatedEvent(VendorCreatedEvent event) {
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}
