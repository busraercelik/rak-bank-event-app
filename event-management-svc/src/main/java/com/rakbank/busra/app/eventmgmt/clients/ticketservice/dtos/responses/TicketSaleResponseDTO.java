package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.requests.TicketSaleRequestDTO;
import lombok.Data;

@Data
public class TicketSaleResponseDTO extends TicketSaleRequestDTO {
    String referenceId;
}
