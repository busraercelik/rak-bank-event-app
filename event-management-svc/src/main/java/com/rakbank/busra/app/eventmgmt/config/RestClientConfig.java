package com.rakbank.busra.app.eventmgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(ResponseCodeInterceptor responseCodeInterceptor){
        return RestClient.builder()
                .requestInterceptor(responseCodeInterceptor)
                .defaultHeader("X-Trace-ID", UUID.randomUUID().toString())
                .build();
    }

}
