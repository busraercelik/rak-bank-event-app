package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.Currency;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketStatus;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketTypeDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketSaleDTO {
    Long id;
    TicketTypeDTO ticketType;

    Long userId;
    Long eventId;
    Long paymentId;

    BigDecimal amount;
    Currency currency;
    TicketStatus ticketStatus;
    String referenceId;
}