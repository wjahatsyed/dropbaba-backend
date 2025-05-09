package com.dropbaba.backend.notificationservice.model;

import lombok.Data;

@Data
public class Notification {
    private String title;
    private String recipient;
    private String body;
}
