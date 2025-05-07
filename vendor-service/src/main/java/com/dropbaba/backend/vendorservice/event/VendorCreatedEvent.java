package com.dropbaba.backend.vendorservice.event;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorCreatedEvent implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String city;
}

