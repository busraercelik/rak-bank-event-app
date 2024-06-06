package com.rakbank.busra.app.ticket.repositories;


import com.rakbank.busra.app.ticket.models.EventTicketInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface EventTicketInventoryRepository extends JpaRepository<EventTicketInventory, Long> {

    EventTicketInventory findByEventId(@NonNull Long eventId);
}
