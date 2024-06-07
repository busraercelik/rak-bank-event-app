package com.rakbank.busra.app.eventmgmt.clients.eventservice;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.config.AppConfig;
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

    public BaseAPIResponse<EventDTO> getById(Long id) {
        return restClient.get()
                .uri(appConfig.getEventServiceBaseUrl() + BASE_PATH+"/{id}", id)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<EventDTO>>() {})
                .getBody();
    }

    public BaseAPIResponse<List<EventDTO>> search(String search) {
        return restClient.get()
                .uri(appConfig.getEventServiceBaseUrl() + BASE_PATH+"?search={search}", search)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<List<EventDTO>>>() {})
                .getBody();

    }

    public BaseAPIResponse<EventDTO> updateWithInventoryId(Long eventId, Long inventoryId) {
        return restClient.put()
                .uri(appConfig.getEventServiceBaseUrl()
                        + BASE_PATH+"/link/{eventId}/{inventoryId}", eventId, inventoryId)
                .contentType(APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<EventDTO>>() {})
                .getBody();
    }

}
