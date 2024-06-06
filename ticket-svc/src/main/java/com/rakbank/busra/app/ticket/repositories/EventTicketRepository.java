package com.rakbank.busra.app.ticket.repositories;


import com.rakbank.busra.app.ticket.common.exceptions.ApplicationException;
import com.rakbank.busra.app.ticket.errors.ErrorCode;
import com.rakbank.busra.app.ticket.models.EventTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface EventTicketRepository extends JpaRepository<EventTicket, Long> {

    Optional<EventTicket> findByEventTicketInventory_EventId(@NonNull Long eventId);
    default EventTicket getByEventTicketInventoryByEventId(@NonNull Long eventId){
        return findByEventTicketInventory_EventId(eventId)
                .orElseThrow(()-> new ApplicationException(
                        String.format("EventTicket for event : %s not found", eventId),
                        ErrorCode.TICKET_EVENT_NOT_FOUND));
    }
}
