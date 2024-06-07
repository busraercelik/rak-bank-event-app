package com.rakbank.busra.app.eventmgmt.clients.paymentservice;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@AllArgsConstructor
@Component
public class PaymentClient {
    public static final String BASE_PATH = "/v1/payment";
    private final AppConfig appConfig;
    private final RestClient restClient;

    public BaseAPIResponse<PaymentDTO> create(PaymentDTO request) {
        return restClient.post()
                .uri(appConfig.getPaymentServiceBaseUrl() + BASE_PATH)
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<PaymentDTO>>() {
                })
                .getBody();
    }

    BaseAPIResponse<PaymentDTO> complete(Long paymentId) {
        return restClient.put()
                .uri(appConfig.getPaymentServiceBaseUrl() + BASE_PATH + "/complete/{paymentId}", paymentId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<PaymentDTO>>() {
                })
                .getBody();
    }

    BaseAPIResponse<PaymentDTO> cancel(Long paymentId) {
        return restClient.delete()
                .uri(appConfig.getPaymentServiceBaseUrl() + BASE_PATH + "/cancel/{paymentId}", paymentId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<PaymentDTO>>() {
                })
                .getBody();
    }
}
