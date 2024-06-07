package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketTypeDTO;
import lombok.Data;

@Data
public class EventTicketResponseDTO {
    Long id;
    TicketTypeDTO ticketType;
    int totalTickets;
    int availableTickets;
}
