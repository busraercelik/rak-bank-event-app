package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketStatus;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketTypeDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.requests.TicketSaleRequestDTO;
import lombok.Data;

@Data
public class TicketSaleResponseDTO extends TicketSaleRequestDTO {
    Long id;
    Long paymentId;
    String referenceId;
    TicketTypeDTO ticketType;
    TicketStatus ticketStatus;
}
