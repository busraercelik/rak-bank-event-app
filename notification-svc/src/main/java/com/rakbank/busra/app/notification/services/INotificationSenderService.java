package com.rakbank.busra.app.notification.services;

import com.rakbank.busra.app.notification.models.Notification;

public interface INotificationSenderService {
    void sendNotification(Notification dto);
    boolean supportsNotificationType(Notification dto);
}
