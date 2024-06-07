package com.rakbank.busra.app.eventmgmt.dtos.responses;

import com.rakbank.busra.app.eventmgmt.dtos.NotificationStatus;
import com.rakbank.busra.app.eventmgmt.dtos.NotificationType;
import lombok.Data;

@Data
public class NotificationResponseDTO {
  Long id;
  private String recipient;
  private String subject;
  private String message;
  private Integer tries = 0;
  private NotificationType notificationType;
  private NotificationStatus status;
}
