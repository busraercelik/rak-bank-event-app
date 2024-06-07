package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.request;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.commons.Currency;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.commons.TicketStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketSaleRequestDTO {
    String ticketTypeName;
    Long userId;
    Long eventId;
    Long paymentId;
    BigDecimal amount;
    Currency currency;
    TicketStatus ticketStatus;
}
