package com.rakbank.busra.app.notification.mappers;

import com.rakbank.busra.app.notification.dtos.NotificationDTO;
import com.rakbank.busra.app.notification.models.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification toNotificationEntity(NotificationDTO dto);
}