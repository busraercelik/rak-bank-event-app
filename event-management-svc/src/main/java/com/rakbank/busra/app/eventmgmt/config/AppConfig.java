package com.rakbank.busra.app.eventmgmt.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppConfig {
    @Value("${user-svc.baseurl}")
    String userServiceBaseUrl;

    @Value("${ticket-svc.baseurl}")
    String ticketServiceBaseUrl;

    @Value("${payment-svc.baseurl}")
    String paymentServiceBaseUrl;

    @Value("${notification-svc.baseurl}")
    String notificationServiceBaseUrl;

    @Value("${event-svc.baseurl}")
    String eventServiceBaseUrl;
}
