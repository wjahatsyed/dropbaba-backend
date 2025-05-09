package com.dropbaba.backend.notificationservice.websocket;

import com.dropbaba.backend.notificationservice.model.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

class NotificationBroadcasterTest {

    @Test
    void shouldBroadcastNotificationToTopic() {
        // Arrange
        SimpMessagingTemplate messagingTemplate = mock(SimpMessagingTemplate.class);
        NotificationBroadcaster broadcaster = new NotificationBroadcaster(messagingTemplate);

        Notification notification = new Notification();
        notification.setTitle("Test Title");
        notification.setBody("Test Body");
        notification.setRecipient("test@example.com");

        // Act
        broadcaster.broadcast(notification);

        // Assert
        verify(messagingTemplate, times(1))
                .convertAndSend("/topic/notifications", notification);
    }
}
