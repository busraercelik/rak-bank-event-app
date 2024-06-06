package com.rakbank.busra.app.user.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class EventTicketInventory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    int eventId;
    List<EventTicket> eventTickets;
}
