package com.dropbaba.backend.notificationservice;


import com.dropbaba.backend.notificationservice.event.VendorCreatedEvent;
import com.dropbaba.backend.notificationservice.listener.VendorEventListener;
import org.junit.jupiter.api.Test;

class VendorEventListenerTest {

    private final VendorEventListener listener = new VendorEventListener();

    @Test
    void shouldHandleVendorCreatedEvent() {
        VendorCreatedEvent event = VendorCreatedEvent.builder()
                .id(1L)
                .name("Biryani Express")
                .email("contact@biryaniexpress.pk")
                .city("Karachi")
                .build();

        listener.handleVendorCreated(event);

    }
}

