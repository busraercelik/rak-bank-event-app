package com.rakbank.busra.app.eventmgmt.clients;

import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.dtos.PaymentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@AllArgsConstructor
@Component
public class PaymentClient {
    private final AppConfig appConfig;
    private final RestClient restClient;

    public PaymentDTO create(PaymentDTO request) {
        return restClient.post()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/payment")
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(PaymentDTO.class)
                .getBody();
    }

    public PaymentDTO update(PaymentDTO request) {
        return restClient.put()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/payment")
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(PaymentDTO.class)
                .getBody();
    }
}
