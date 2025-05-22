package com.dropbaba.backend.deliveryservice.controller;

import com.dropbaba.backend.deliveryservice.model.Delivery;
import com.dropbaba.backend.deliveryservice.model.DeliveryStatus;
import com.dropbaba.backend.deliveryservice.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Delivery> getDeliveryByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(deliveryService.getDeliveryByOrderId(orderId));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable String orderId,
            @RequestParam DeliveryStatus status) {

        deliveryService.updateStatus(orderId, status);
        return ResponseEntity.ok().build();
    }

}
