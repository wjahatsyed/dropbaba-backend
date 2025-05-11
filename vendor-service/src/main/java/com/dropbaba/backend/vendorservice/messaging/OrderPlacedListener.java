package com.dropbaba.backend.vendorservice.messaging;

import com.dropbaba.backend.vendorservice.event.OrderPlacedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPlacedListener {

    @RabbitListener(queues = "${rabbitmq.vendor.queue}")
    public void handleOrderPlaced(OrderPlacedEvent event) {
        System.out.println("Received OrderPlacedEvent in vendor-service:");
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("User ID: " + event.getUserId());
        System.out.println("Vendor ID: " + event.getVendorId());
        System.out.println("Total Amount: " + event.getTotalAmount());

        event.getItems().forEach(item -> {
            System.out.println("Product:" + item.getProductName() +
                    " x" + item.getQuantity() +
                    " = " + item.getPrice());
        });

        // TODO:
        // - Save event info to DB (optional)
        // - Trigger vendor logic (e.g., mark as "PREPARING")
        // - Publish OrderReadyForDeliveryEvent when ready
    }
}
