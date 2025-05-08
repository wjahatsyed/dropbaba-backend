package com.dropbaba.backend.notificationservice.listener;

import com.dropbaba.backend.notificationservice.event.VendorCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VendorEventListener {

    @RabbitListener(queues = "${rabbitmq.vendor.queue}")
    public void handleVendorCreated(VendorCreatedEvent event) {
        System.out.printf("📢 Sending welcome email to vendor: %s (%s) in %s%n",
                event.getName(), event.getEmail(), event.getCity());
    }
}
