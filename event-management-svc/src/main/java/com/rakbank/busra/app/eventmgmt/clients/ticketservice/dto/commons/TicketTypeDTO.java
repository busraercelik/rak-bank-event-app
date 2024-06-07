package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.commons;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketTypeDTO {
    Long id;
    String ticketTypeName;
    BigDecimal amount;
    Currency currency;
}