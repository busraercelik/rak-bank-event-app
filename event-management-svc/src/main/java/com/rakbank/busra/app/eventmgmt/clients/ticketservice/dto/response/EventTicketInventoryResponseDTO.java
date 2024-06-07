package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EventTicketInventoryResponseDTO {

    Long id;
    Long eventId;
    List<EventTicketResponseDTO> eventTickets = new ArrayList<>();
}
