package com.dropbaba.backend.deliveryservice.messaging;

import com.dropbaba.backend.deliveryservice.event.OrderReadyForDeliveryEvent;
import com.dropbaba.backend.deliveryservice.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class OrderReadyEventListenerTest {

    private DeliveryService deliveryService;
    private OrderReadyEventListener listener;

    @BeforeEach
    void setUp() {
        deliveryService = mock(DeliveryService.class);
        listener = new OrderReadyEventListener(deliveryService);
    }

    @Test
    void handleOrderReadyEvent_callsService() {
        OrderReadyForDeliveryEvent event = new OrderReadyForDeliveryEvent();
        event.setOrderId("order123");
        event.setVendorId("vendorABC");
        event.setUserId("userXYZ");

        listener.handleOrderReadyEvent(event);

        verify(deliveryService, times(1)).createDelivery(event);
    }
}
