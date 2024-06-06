package com.rakbank.busra.app.ticket.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class TicketType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String ticketTypeName;
    BigDecimal amount;
    Currency currency;
}
