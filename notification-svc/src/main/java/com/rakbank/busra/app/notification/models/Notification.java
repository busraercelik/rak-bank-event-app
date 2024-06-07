package com.rakbank.busra.app.notification.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String recipient;
    @Column(length = 500)
    private String subject;
    @Column(length = 5000)
    private String message;
    private Integer tries = 0;
    private NotificationType notificationType;
    private NotificationStatus status;
}
