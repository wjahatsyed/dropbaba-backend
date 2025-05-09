package com.dropbaba.backend.notificationservice.websocket;

import com.dropbaba.backend.notificationservice.model.Notification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationBroadcaster {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationBroadcaster(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcast(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
