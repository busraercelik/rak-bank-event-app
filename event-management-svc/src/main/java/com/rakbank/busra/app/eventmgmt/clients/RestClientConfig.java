package com.rakbank.busra.app.eventmgmt.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(){
        return RestClient.builder()
                .defaultHeader("X-Trace-ID", UUID.randomUUID().toString())
                .build();
    }
}
