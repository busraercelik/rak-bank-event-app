package com.rakbank.busra.app.ticket.repositories;


import com.rakbank.busra.app.ticket.models.EventTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTicketRepository extends JpaRepository<EventTicket, Long> {

}
