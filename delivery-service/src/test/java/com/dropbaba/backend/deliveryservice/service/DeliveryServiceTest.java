package com.dropbaba.backend.deliveryservice.service;

import com.dropbaba.backend.deliveryservice.event.OrderReadyForDeliveryEvent;
import com.dropbaba.backend.deliveryservice.messaging.DeliveryEventPublisher;
import com.dropbaba.backend.deliveryservice.model.Delivery;
import com.dropbaba.backend.deliveryservice.model.DeliveryStatus;
import com.dropbaba.backend.deliveryservice.repository.DeliveryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {

    private DeliveryRepository repository;
    private DeliveryService service;

    @BeforeEach
    void setUp() {
        repository = mock(DeliveryRepository.class);
        DeliveryEventPublisher deliveryEventPublisher = mock(DeliveryEventPublisher.class);
        service = new DeliveryService(repository, deliveryEventPublisher);

    }

    @Test
    void createDelivery_savesDeliveryCorrectly() {
        OrderReadyForDeliveryEvent event = new OrderReadyForDeliveryEvent();
        event.setOrderId("order123");
        event.setVendorId("vendorABC");
        event.setUserId("userXYZ");

        service.createDelivery(event);

        ArgumentCaptor<Delivery> captor = ArgumentCaptor.forClass(Delivery.class);
        verify(repository, times(1)).save(captor.capture());

        Delivery saved = captor.getValue();
        assertEquals("order123", saved.getOrderId());
        assertEquals("vendorABC", saved.getVendorId());
        assertEquals("userXYZ", saved.getUserId());
        assertEquals(DeliveryStatus.READY, saved.getStatus());
        assertNotNull(saved.getTimestamp());
    }

    @Test
    void getDeliveryByOrderId_returnsDelivery() {
        Delivery mockDelivery = new Delivery();
        mockDelivery.setOrderId("order123");

        when(repository.findByOrderId("order123")).thenReturn(Optional.of(mockDelivery));

        Delivery result = service.getDeliveryByOrderId("order123");
        assertEquals("order123", result.getOrderId());
    }

    @Test
    void getDeliveryByOrderId_throwsIfNotFound() {
        when(repository.findByOrderId("missing")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getDeliveryByOrderId("missing"));
    }
}
