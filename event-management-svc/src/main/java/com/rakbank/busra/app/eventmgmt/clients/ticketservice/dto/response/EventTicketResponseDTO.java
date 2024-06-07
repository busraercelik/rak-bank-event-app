package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.response;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.commons.TicketTypeDTO;
import lombok.Data;

@Data
public class EventTicketResponseDTO {
    Long id;
    TicketTypeDTO ticketType;
    int totalTickets;
    int availableTickets;
}
