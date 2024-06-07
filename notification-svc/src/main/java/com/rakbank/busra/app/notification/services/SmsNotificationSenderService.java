package com.rakbank.busra.app.notification.services;


import com.rakbank.busra.app.notification.models.Notification;
import com.rakbank.busra.app.notification.models.NotificationType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SmsNotificationSenderService implements INotificationSenderService {

    @Override
    public void sendNotification(Notification dto) {
        log.info("dummy implementation.. because I don't have money for sms api..");
    }

    @Override
    public boolean supportsNotificationType(Notification dto) {
        return dto.getNotificationType() == NotificationType.SMS;
    }
}
