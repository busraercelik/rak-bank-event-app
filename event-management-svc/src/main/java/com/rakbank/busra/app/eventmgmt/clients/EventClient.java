package com.rakbank.busra.app.eventmgmt.clients;

import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.dtos.EventCreateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@AllArgsConstructor
@Component
public class EventClient {
    private final AppConfig appConfig;
    private final RestClient restClient;

    public EventCreateRequestDTO createEvent(EventCreateRequestDTO request) {
        return restClient.post()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/event")
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(EventCreateRequestDTO.class)
                .getBody();
    }

    public EventCreateRequestDTO getById(String id) {
        return restClient.get()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/event/{id}", id)
                .retrieve()
                .body(EventCreateRequestDTO.class);
    }

    public List<EventCreateRequestDTO> search(String search) {
        return restClient.get()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/event?search={search}", search)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<EventCreateRequestDTO>>() {})
                .getBody();

    }
}
