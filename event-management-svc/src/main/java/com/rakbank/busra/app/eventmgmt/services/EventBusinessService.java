package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.EventClient;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.TicketClient;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.dtos.requests.EventDTO;
import com.rakbank.busra.app.eventmgmt.dtos.responses.EventResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventBusinessService {

    private final EventClient eventClient;
    private final TicketClient ticketClient;

    //TODO
    public EventResponseDTO create(EventDTO dto) {
        //create the event
        var event = eventClient.createEvent(dto);
        //create the event inventory
        return new EventResponseDTO();
    }

    public BaseAPIResponse<EventDTO> fetch(String id) {
        return eventClient.getById(id);
    }

    public List<EventDTO> search(String search) {
        return eventClient.search(search);
    }
}
