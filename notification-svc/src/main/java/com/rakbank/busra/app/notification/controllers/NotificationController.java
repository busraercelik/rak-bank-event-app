package com.rakbank.busra.app.notification.controllers;


import com.rakbank.busra.app.notification.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.notification.dtos.NotificationDTO;
import com.rakbank.busra.app.notification.models.Notification;
import com.rakbank.busra.app.notification.services.INotificationSenderService;
import com.rakbank.busra.app.notification.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/notify")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    BaseAPIResponse<Notification> processNotification(@RequestBody NotificationDTO dto) {
        var result = notificationService.processNotification(dto);
        return new BaseAPIResponse<>("200","Notification processed successfully", result);
    }

}
