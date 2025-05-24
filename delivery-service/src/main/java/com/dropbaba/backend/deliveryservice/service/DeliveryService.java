package com.dropbaba.backend.deliveryservice.service;

import com.dropbaba.backend.deliveryservice.event.DeliveryStatusChangedEvent;
import com.dropbaba.backend.deliveryservice.event.OrderReadyForDeliveryEvent;
import com.dropbaba.backend.deliveryservice.messaging.DeliveryEventPublisher;
import com.dropbaba.backend.deliveryservice.model.Delivery;
import com.dropbaba.backend.deliveryservice.model.DeliveryStatus;
import com.dropbaba.backend.deliveryservice.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryService {

    private final DeliveryRepository repository;
    private DeliveryEventPublisher eventPublisher;

    public DeliveryService(DeliveryRepository repository, DeliveryEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void createDelivery(OrderReadyForDeliveryEvent event) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(event.getOrderId());
        delivery.setVendorId(event.getVendorId());
        delivery.setUserId(event.getUserId());
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setTimestamp(LocalDateTime.now());

        repository.save(delivery);

        DeliveryStatusChangedEvent deliveryStatusChangedEvent = new DeliveryStatusChangedEvent(
                delivery.getOrderId(),
                delivery.getUserId(),
                delivery.getVendorId(),
                DeliveryStatus.READY,
                "Your order is now " + DeliveryStatus.READY
        );
        eventPublisher.publishDeliveryStatusChangedEvent(deliveryStatusChangedEvent);
    }

    public Delivery getDeliveryByOrderId(String orderId) {
        return repository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
    }

    public void updateStatus(String orderId, DeliveryStatus newStatus) {
        Delivery delivery = repository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(newStatus);
        delivery.setTimestamp(LocalDateTime.now());
        repository.save(delivery);

        DeliveryStatusChangedEvent deliveryStatusChangedEvent = new DeliveryStatusChangedEvent(
                delivery.getOrderId(),
                delivery.getUserId(),
                delivery.getVendorId(),
                newStatus,
                "Your order is now " + newStatus
        );
        eventPublisher.publishDeliveryStatusChangedEvent(deliveryStatusChangedEvent);
    }

}
