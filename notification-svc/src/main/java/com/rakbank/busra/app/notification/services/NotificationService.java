package com.rakbank.busra.app.notification.services;

import com.rakbank.busra.app.notification.common.exceptions.ApplicationException;
import com.rakbank.busra.app.notification.config.AppConfig;
import com.rakbank.busra.app.notification.dtos.NotificationDTO;
import com.rakbank.busra.app.notification.mappers.NotificationMapper;
import com.rakbank.busra.app.notification.models.Notification;
import com.rakbank.busra.app.notification.models.NotificationStatus;
import com.rakbank.busra.app.notification.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class NotificationService {

    private final AppConfig appConfig;
    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;
    private final List<INotificationSenderService> notificationSenders;

    public Notification processNotification(NotificationDTO dto) {
        log.info("Saving notification to process later.. {}", dto);
        var notification = notificationMapper.toNotificationEntity(dto);
        notification.setStatus(NotificationStatus.PENDING);
        notificationRepository.save(notification);
        return notification;
    }

    @Scheduled(fixedRate = 5000)
    public void notificationSenderJob(){
        var notificationBatchSize = appConfig.getNotificationBatchSize();
        var notifications = notificationRepository.findByStatus(
                NotificationStatus.PENDING, Pageable.ofSize(notificationBatchSize));
        log.info("Found {} pending notifications to send..", notifications.size());

        // send each notification using senders
        notifications.forEach(this::sendNotification);
    }

    private void sendNotification(Notification notification) {
        notificationSenders
                .stream()
                .filter(sender-> sender.supportsNotificationType(notification))
                .findFirst()
                .ifPresentOrElse(
                        selectedSender -> sendNotification(notification, selectedSender),
                        ()-> handleSenderNotSupported(notification));
    }

    private void sendNotification(Notification notification, INotificationSenderService selectedSender) {
        var tries = notification.getTries();
        try {
            selectedSender.sendNotification(notification);
            notification.setStatus(NotificationStatus.SENT);
        } catch (Exception ex){
            tries++;
            log.warn("Failed to send notification {}, reason [{}]", notification, ex.getMessage(), ex);
        } finally {
            if(tries>appConfig.getMaxRetry()){
                // we have reached the limit of retries
                notification.setStatus(NotificationStatus.FAILED);
            } else {
                // update notification tries count
                notification.setTries(tries+1);
            }
            notificationRepository.save(notification);
            log.info("Successfully sent notification {}", notification);
        }

    }

    private void handleSenderNotSupported(Notification notification) {
        notification.setStatus(NotificationStatus.FAILED);
        notificationRepository.save(notification);
        log.info("Failed to send notification {}", notification);
    }
}
