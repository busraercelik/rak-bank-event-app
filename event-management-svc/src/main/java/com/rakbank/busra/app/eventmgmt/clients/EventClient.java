package com.rakbank.busra.app.eventmgmt.clients;

import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.dtos.EventDTO;
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

    public EventDTO createEvent(EventDTO request) {
        return restClient.post()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/event")
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(EventDTO.class)
                .getBody();
    }

    public EventDTO getById(String id) {
        return restClient.get()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/event/{id}", id)
                .retrieve()
                .body(EventDTO.class);
    }

    public List<EventDTO> search(String search) {
        return restClient.get()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/event?search={search}", search)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<EventDTO>>() {})
                .getBody();

    }
}
