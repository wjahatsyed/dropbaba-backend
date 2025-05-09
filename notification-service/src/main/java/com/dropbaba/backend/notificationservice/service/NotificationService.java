package com.dropbaba.backend.notificationservice.service;

import com.dropbaba.backend.notificationservice.model.Notification;

public interface NotificationService {
    void sendNotification(Notification notification);
}
