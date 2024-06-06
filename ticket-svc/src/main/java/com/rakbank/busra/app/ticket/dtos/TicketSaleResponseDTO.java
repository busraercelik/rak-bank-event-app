package com.rakbank.busra.app.ticket.dtos;

import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class TicketSaleResponseDTO extends TicketSaleRequestDTO{
    @Null(message = "field referenceId cannot be set")
    String referenceId;
}
