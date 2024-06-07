package com.rakbank.busra.app.eventmgmt.dtos.responses;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.EventTicketResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventViewResponse {
    EventDTO event;
    List<EventTicketResponseDTO> ticketAvailability;
}
