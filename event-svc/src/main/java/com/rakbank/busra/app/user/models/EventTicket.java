package com.rakbank.busra.app.user.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EventTicket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    int eventId;
    int ticketTypeId;
    int total;
    int available;
}
