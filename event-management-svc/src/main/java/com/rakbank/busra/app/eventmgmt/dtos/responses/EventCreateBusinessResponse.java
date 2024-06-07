package com.rakbank.busra.app.eventmgmt.dtos.responses;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.EventTicketResponseDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCreateBusinessResponse {
    Long id;
    String name;
    String description;
    String host;

    String dateTo;
    String dateFrom;
    Long eventTicketInventoryId;
    List<EventTicketResponseDTO> eventTickets = new ArrayList<>();
}
