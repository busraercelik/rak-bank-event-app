package com.rakbank.busra.app.notification.dtos;

import com.rakbank.busra.app.notification.models.NotificationType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationDTO {
    @NotNull(message = "recipient cannot be empty")
    private String recipient;
    private String subject;
    @NotNull(message = "message cannot be empty")
    private String message;
    private NotificationType notificationType;
}
