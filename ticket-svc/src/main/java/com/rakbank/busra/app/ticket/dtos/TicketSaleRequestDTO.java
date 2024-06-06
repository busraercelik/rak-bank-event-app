package com.rakbank.busra.app.ticket.dtos;

import com.rakbank.busra.app.ticket.models.Currency;
import com.rakbank.busra.app.ticket.models.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketSaleRequestDTO {

    @NotNull(message = "ticketTypeName cannot be empty")
    String ticketTypeName;

    @NotNull(message = "userId cannot be empty")
    Long userId;
    @NotNull(message = "eventId cannot be empty")
    Long eventId;
    @NotNull(message = "paymentId cannot be empty")
    Long paymentId;

    @NotNull(message = "amount cannot be empty")
    BigDecimal amount;
    @NotNull(message = "currency cannot be empty")
    Currency currency;
    @NotNull(message = "ticketStatus cannot be empty")
    TicketStatus ticketStatus;
}
