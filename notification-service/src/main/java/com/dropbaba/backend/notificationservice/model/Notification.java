package com.dropbaba.backend.notificationservice.model;

import lombok.*;


import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String userId;
    private String title;
    private String recipient;
    private String body;
    private LocalDateTime timestamp;
}
