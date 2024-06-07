package com.rakbank.busra.app.eventmgmt.clients.notificationservice;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.requests.NotificationRequestDTO;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.responses.NotificationResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class NotificationClient {

    public static final String BASE_PATH = "/v1/notify";
    private final AppConfig appConfig;
    private final RestClient restClient;

    public BaseAPIResponse<NotificationResponseDTO> notifyUser(NotificationRequestDTO request) {
        return restClient.post()
                .uri(appConfig.getUserServiceBaseUrl() + BASE_PATH)
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<NotificationResponseDTO>>() {})
                .getBody();
    }
}


