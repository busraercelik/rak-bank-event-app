package com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.responses;

import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.NotificationStatus;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.NotificationType;
import lombok.Data;

@Data
public class NotificationResponseDTO {
  Long id;
  private String recipient;
  private String subject;
  private String message;
  private NotificationType notificationType;
  private NotificationStatus status;
}
