package com.rakbank.busra.app.eventmgmt.clients;

import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.dtos.TicketDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@AllArgsConstructor
@Component
public class TicketClient {
    private final AppConfig appConfig;
    private final RestClient restClient;

    public TicketDTO create(TicketDTO request) {
        return restClient.post()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/ticket")
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(TicketDTO.class)
                .getBody();
    }

    public TicketDTO cancel(String id) {
        return restClient.delete()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/ticket/{id}", id)
                .retrieve()
                .toEntity(TicketDTO.class)
                .getBody();
    }
}
