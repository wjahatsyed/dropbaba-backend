package com.dropbaba.backend.deliveryservice.event;

import com.dropbaba.backend.deliveryservice.model.DeliveryStatus;
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
    private DeliveryStatus deliveryStatus;
    private String message;
}
