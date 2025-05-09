package com.dropbaba.backend.notificationservice.service;

import com.dropbaba.backend.notificationservice.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void sendNotification(Notification notification) {
        // Simulate sending logic
        logger.info("📢 Sending notification to {}: {}", notification.getRecipient(), notification.getTitle());
        // TODO: future enhancement - send via Email/SMS/WebSocket
    }
}
