package com.dropbaba.backend.deliveryservice.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReadyForDeliveryEvent {
    private String orderId;
    private String vendorId;
    private String userId;
}
