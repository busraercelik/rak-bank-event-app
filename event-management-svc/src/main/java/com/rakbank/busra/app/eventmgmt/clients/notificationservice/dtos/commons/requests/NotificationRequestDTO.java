package com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.requests;


import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.NotificationType;
import lombok.Data;

@Data
public class NotificationRequestDTO {
  private String recipient;
  private String subject;
  private String message;
  private NotificationType notificationType;
}
