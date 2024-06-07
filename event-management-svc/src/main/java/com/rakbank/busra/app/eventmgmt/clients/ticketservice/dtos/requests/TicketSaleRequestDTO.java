package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.requests;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketSaleRequestDTO {
    String ticketTypeName;
    Long userId;
    Long eventId;
    BigDecimal amount;
    Currency currency;
}
