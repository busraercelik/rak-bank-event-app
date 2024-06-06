package com.rakbank.busra.app.ticket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class EventTicketInventory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    Long eventId;

    @OneToMany(mappedBy = "eventTicketInventory", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    List<EventTicket> eventTickets = new ArrayList<>();
}
