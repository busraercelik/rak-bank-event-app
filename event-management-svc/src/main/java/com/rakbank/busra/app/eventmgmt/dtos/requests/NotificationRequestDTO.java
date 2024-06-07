package com.rakbank.busra.app.eventmgmt.dtos.requests;


import com.rakbank.busra.app.eventmgmt.dtos.NotificationType;
import lombok.Data;

@Data
public class NotificationRequestDTO {
  private String recipient;
  private String subject;
  private String message;
  private NotificationType notificationType;
}
