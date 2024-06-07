package com.rakbank.busra.app.eventmgmt.clients.eventservice;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@AllArgsConstructor
@Component
public class EventClient {
    public static final String BASE_PATH = "/v1/event";

    private final AppConfig appConfig;
    private final RestClient restClient;

    public BaseAPIResponse<EventDTO> createEvent(EventDTO request) {
        return restClient.post()
                .uri(appConfig.getEventServiceBaseUrl() + BASE_PATH)
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<EventDTO>>() {})
                .getBody();
    }

    public BaseAPIResponse<EventDTO> getById(String id) {
        return restClient.get()
                .uri(appConfig.getEventServiceBaseUrl() + BASE_PATH+"/{id}", id)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<EventDTO>>() {})
                .getBody();
    }

    public List<EventDTO> search(String search) {
        return restClient.get()
                .uri(appConfig.getEventServiceBaseUrl() + BASE_PATH+"?search={search}", search)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<EventDTO>>() {})
                .getBody();

    }
}
