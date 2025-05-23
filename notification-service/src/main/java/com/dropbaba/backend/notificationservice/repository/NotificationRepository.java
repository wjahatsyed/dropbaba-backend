package com.dropbaba.backend.notificationservice.repository;
import com.dropbaba.backend.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
