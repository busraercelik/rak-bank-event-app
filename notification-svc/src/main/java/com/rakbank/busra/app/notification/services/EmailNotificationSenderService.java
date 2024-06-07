package com.rakbank.busra.app.notification.services;


import com.rakbank.busra.app.notification.config.AppConfig;
import com.rakbank.busra.app.notification.models.Notification;
import com.rakbank.busra.app.notification.models.NotificationType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@AllArgsConstructor
@Service
public class EmailNotificationSenderService implements INotificationSenderService {

    private final AppConfig appConfig;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendNotification(Notification dto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(appConfig.getEmailUserName());
        mailMessage.setTo(dto.getRecipient());
        mailMessage.setText(dto.getMessage());
        mailMessage.setSubject(dto.getSubject());
        javaMailSender.send(mailMessage);
    }

    @Override
    public boolean supportsNotificationType(Notification dto) {
        return dto.getNotificationType() == NotificationType.EMAIL;
    }
}

