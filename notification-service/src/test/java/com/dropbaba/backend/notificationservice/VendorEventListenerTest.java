package com.dropbaba.backend.notificationservice;

import com.dropbaba.backend.notificationservice.event.VendorCreatedEvent;
import com.dropbaba.backend.notificationservice.listener.VendorEventListener;
import com.dropbaba.backend.notificationservice.model.Notification;
import com.dropbaba.backend.notificationservice.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class VendorEventListenerTest {

    private NotificationService notificationService;
    private VendorEventListener listener;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        listener = new VendorEventListener(notificationService);
    }

    @Test
    void shouldHandleVendorCreatedEventAndSendNotification() {
        VendorCreatedEvent event = VendorCreatedEvent.builder()
                .id(1L)
                .name("Biryani Express")
                .email("contact@biryaniexpress.pk")
                .city("Karachi")
                .build();

        listener.handleVendorCreated(event);

        ArgumentCaptor<Notification> captor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationService).sendNotification(captor.capture());

        Notification sent = captor.getValue();
        assertThat(sent.getRecipient()).isEqualTo("contact@biryaniexpress.pk");
        assertThat(sent.getTitle()).contains("Biryani Express");
        assertThat(sent.getBody()).contains("Thank you for registering");
    }
}


