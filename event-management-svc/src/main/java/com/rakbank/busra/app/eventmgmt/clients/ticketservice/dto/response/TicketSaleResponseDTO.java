package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.response;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.request.TicketSaleRequestDTO;
import lombok.Data;

@Data
public class TicketSaleResponseDTO extends TicketSaleRequestDTO {
    String referenceId;
}
