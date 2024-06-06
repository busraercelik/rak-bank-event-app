package com.rakbank.busra.app.ticket.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EventTicket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EventTicketInventoryId")
    EventTicketInventory eventTicketInventory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TicketTypeId")
    TicketType ticketType;

    int totalTickets;
    int availableTickets;
}
