package com.rakbank.busra.app.notification.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {
    
    @Value("${notification.max-retry}")
    private Integer maxRetry;
    @Value("${notification.batch-size}")
    private Integer notificationBatchSize;

    //email configuration
    @Value("${spring.mail.host}")
    private String emailHost;
    @Value("${spring.mail.port}")
    private Integer emailPort;
    @Value("${spring.mail.username}")
    private String emailUserName;
    @Value("${spring.mail.password}")
    private String emailPassword;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private Boolean emailSmtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private Boolean emailSmtpAuthStartTlsEnable;
}
