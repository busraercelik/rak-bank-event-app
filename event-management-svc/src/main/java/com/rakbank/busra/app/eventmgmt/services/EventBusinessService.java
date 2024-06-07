package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.EventClient;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.TicketClient;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.dtos.requests.EventCreateBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.responses.EventCreateBusinessResponse;
import com.rakbank.busra.app.eventmgmt.mappers.BusinessRequestMapper;
import com.rakbank.busra.app.eventmgmt.mappers.BusinessResponseMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EventBusinessService {

    private final EventClient eventClient;
    private final TicketClient ticketClient;
    private final BusinessRequestMapper businessRequestMapper;
    private final BusinessResponseMapper businessResponseMapper;

    public EventCreateBusinessResponse create(EventCreateBusinessRequest request) {
        log.info("Creating a new event : {}", request);
        //create the event
        var eventCreateRequest = businessRequestMapper.toEventDTO(request);
        var event = eventClient.createEvent(eventCreateRequest).getResult();

        var inventoryCreateRequest = businessRequestMapper.toEventTicketInventoryRequestDTO(event.getId(), request);
        //create the event inventory
        var inventory = ticketClient.createEventTicketInventory(inventoryCreateRequest).getResult();
        var eventCreateBusinessResponse = businessResponseMapper.toEventCreateBusinessResponse(event, inventory);
        log.info("Finished creating a new event : {}", request);
        return eventCreateBusinessResponse;
    }

    public BaseAPIResponse<EventDTO> fetch(String id) {
        return eventClient.getById(id);
    }

    public BaseAPIResponse<List<EventDTO>> search(String search) {
        return eventClient.search(search);
    }
}
