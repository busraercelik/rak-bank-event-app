package com.rakbank.busra.app.notification.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String recipient;
    private String subject;
    private String message;
    private Integer tries = 0;
    private NotificationType notificationType;
    private NotificationStatus status;
}
