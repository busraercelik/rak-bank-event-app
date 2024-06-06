package com.rakbank.busra.app.ticket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class TicketSale {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "TicketTypeId")
    TicketType ticketType;

    Long userId;
    Long eventId;
    Long paymentId;

    BigDecimal amount;
    Currency currency;
    TicketStatus ticketStatus;

    String referenceId;
}
