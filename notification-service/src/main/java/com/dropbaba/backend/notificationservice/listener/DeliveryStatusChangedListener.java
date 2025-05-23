package com.dropbaba.backend.notificationservice.listener;

import com.dropbaba.backend.notificationservice.event.DeliveryStatusChangedEvent;
import com.dropbaba.backend.notificationservice.model.Notification;
import com.dropbaba.backend.notificationservice.repository.NotificationRepository;
import com.dropbaba.backend.notificationservice.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.time.LocalDateTime;

public class DeliveryStatusChangedListener {
    private final NotificationRepository notificationRepository;

    public DeliveryStatusChangedListener(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = "delivery.status.changed.queue")
    public void handleDeliveryStatusChanged(DeliveryStatusChangedEvent event) {
        Notification notification = new Notification();
        notification.setUserId(event.getUserId());
        notification.setBody(event.getMessage());
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }

}
