package com.rakbank.busra.app.eventmgmt.clients.ticketservice;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketTypeDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.requests.EventTicketInventoryRequestDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.requests.TicketSaleRequestDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.CreateTicketEventInventoryResultDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.EventTicketInventoryResponseDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleResponseDTO;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class TicketClient {

    private static final String TICKET_BOOKING_BASE_URL = "/v1/ticket/booking";
    private static final String TICKET_INVENTORY_BASE_URL = "/v1/ticket/inventory";

    private final AppConfig appConfig;
    private final RestClient restClient;

    public BaseAPIResponse<TicketSaleResponseDTO> createTicketSale(TicketSaleRequestDTO request) {
        return restClient.post()
                .uri(appConfig.getTicketServiceBaseUrl() + TICKET_BOOKING_BASE_URL)
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<TicketSaleResponseDTO>>() {
                })
                .getBody();
    }

    public BaseAPIResponse<TicketSaleResponseDTO> fetchTicketByReferenceId(String referenceId) {
        return restClient.get()
                .uri(appConfig.getTicketServiceBaseUrl() + TICKET_BOOKING_BASE_URL + "/{referenceId}", referenceId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<TicketSaleResponseDTO>>() {
                })
                .getBody();
    }

    public BaseAPIResponse<TicketSaleDTO> cancelTicketSale(String referenceId) {
        return restClient.put()
                .uri(appConfig.getTicketServiceBaseUrl() + TICKET_BOOKING_BASE_URL + "/{referenceId}", referenceId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<TicketSaleDTO>>() {
                })
                .getBody();
    }

    public BaseAPIResponse<CreateTicketEventInventoryResultDTO> createEventTicketInventory(EventTicketInventoryRequestDTO dto) {
        return restClient.post()
                .uri(appConfig.getTicketServiceBaseUrl() + TICKET_INVENTORY_BASE_URL)
                .contentType(APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<CreateTicketEventInventoryResultDTO>>() {
                })
                .getBody();
    }

    public BaseAPIResponse<EventTicketInventoryResponseDTO> getEventTicketInventoryByEventId(Long eventId) {
        return restClient.get()
                .uri(appConfig.getTicketServiceBaseUrl() + TICKET_INVENTORY_BASE_URL + "/event/{eventId}", eventId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<EventTicketInventoryResponseDTO>>() {
                })
                .getBody();
    }

    public BaseAPIResponse<List<TicketTypeDTO>> getAllTicketTypes() {
        return restClient.get()
                .uri(appConfig.getTicketServiceBaseUrl() + TICKET_INVENTORY_BASE_URL + "/type")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<List<TicketTypeDTO>>>() {
                })
                .getBody();
    }
}
