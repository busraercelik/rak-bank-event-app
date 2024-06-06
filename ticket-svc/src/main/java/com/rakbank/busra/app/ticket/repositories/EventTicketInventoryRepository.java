package com.rakbank.busra.app.ticket.repositories;


import com.rakbank.busra.app.ticket.common.exceptions.ApplicationException;
import com.rakbank.busra.app.ticket.errors.ErrorCode;
import com.rakbank.busra.app.ticket.models.EventTicketInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface EventTicketInventoryRepository extends JpaRepository<EventTicketInventory, Long> {

    Optional<EventTicketInventory> findByEventId(@NonNull Long eventId);

    default EventTicketInventory getByEventId(@NonNull Long eventId){
        return findByEventId(eventId)
                .orElseThrow(()-> new ApplicationException(
                        String.format("EventTicketInventory with id : %s not found", eventId),
                        ErrorCode.TICKET_EVENT_INVENTORY_NOT_FOUND));
    }
}
