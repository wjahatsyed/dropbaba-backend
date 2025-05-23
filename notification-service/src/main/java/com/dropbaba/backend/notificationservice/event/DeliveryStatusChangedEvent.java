package com.dropbaba.backend.notificationservice.event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryStatusChangedEvent {
    private String orderId;
    private String userId;
    private String vendorId;
    private String status;
    private String message;
}