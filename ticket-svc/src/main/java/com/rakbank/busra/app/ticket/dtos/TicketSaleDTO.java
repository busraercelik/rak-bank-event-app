package com.rakbank.busra.app.ticket.dtos;

import com.rakbank.busra.app.ticket.models.Currency;
import com.rakbank.busra.app.ticket.models.TicketStatus;

import java.math.BigDecimal;

public class TicketSaleDTO {
    String name;
    BigDecimal amount;
    Currency currency;
    String ticketTypeName;
    Long userId;
    Long eventId;
    Long paymentId;
    TicketStatus ticketStatus;
}
