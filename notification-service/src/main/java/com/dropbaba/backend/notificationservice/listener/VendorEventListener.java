package com.dropbaba.backend.notificationservice.listener;

import com.dropbaba.backend.notificationservice.event.VendorCreatedEvent;
import com.dropbaba.backend.notificationservice.model.Notification;
import com.dropbaba.backend.notificationservice.service.NotificationService;
import com.dropbaba.backend.notificationservice.websocket.NotificationBroadcaster;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VendorEventListener {

    private final NotificationService notificationService;
    private final NotificationBroadcaster broadcaster;

    public VendorEventListener(NotificationService notificationService, NotificationBroadcaster broadcaster) {
        this.notificationService = notificationService;
        this.broadcaster = broadcaster;
    }


    @RabbitListener(queues = "vendor.created.queue")
    public void handleVendorCreated(VendorCreatedEvent event) {
        Notification notification = new Notification();
        notification.setTitle("Welcome " + event.getName() + "!");
        notification.setBody("Thank you for registering with DropBaba.");
        notification.setRecipient(event.getEmail());

        notificationService.sendNotification(notification);
        broadcaster.broadcast(notification); // WebSocket broadcast
    }
}
