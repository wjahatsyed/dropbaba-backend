package com.dropbaba.backend.deliveryservice.service;

import com.dropbaba.backend.deliveryservice.event.OrderReadyForDeliveryEvent;
import com.dropbaba.backend.deliveryservice.model.Delivery;
import com.dropbaba.backend.deliveryservice.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryService {

    private final DeliveryRepository repository;

    public DeliveryService(DeliveryRepository repository) {
        this.repository = repository;
    }

    public void createDelivery(OrderReadyForDeliveryEvent event) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(event.getOrderId());
        delivery.setVendorId(event.getVendorId());
        delivery.setUserId(event.getUserId());
        delivery.setStatus("READY");
        delivery.setTimestamp(LocalDateTime.now());

        repository.save(delivery);
    }

    public Delivery getDeliveryByOrderId(String orderId) {
        return repository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
    }
}
