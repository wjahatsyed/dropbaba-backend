package com.dropbaba.backend.notificationservice.messaging;

import com.dropbaba.backend.notificationservice.event.VendorCreatedEvent;
import com.dropbaba.backend.notificationservice.model.Notification;
import com.dropbaba.backend.notificationservice.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VendorNotificationListener {

    private final NotificationService notificationService;

    public VendorNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "vendor.created.queue")
    public void handleVendorCreatedEvent(VendorCreatedEvent event) {
        Notification notification = new Notification();
        notification.setTitle("Welcome " + event.getName() + "!");
        notification.setRecipient(event.getEmail());
        notification.setBody("Thank you for registering with DropBaba.");

        notificationService.sendNotification(notification);
    }
}
