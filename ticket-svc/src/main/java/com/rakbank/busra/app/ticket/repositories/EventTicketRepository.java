package com.rakbank.busra.app.ticket.repositories;


import com.rakbank.busra.app.ticket.common.exceptions.ApplicationException;
import com.rakbank.busra.app.ticket.errors.ErrorCode;
import com.rakbank.busra.app.ticket.models.EventTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface EventTicketRepository extends JpaRepository<EventTicket, Long> {

  default EventTicket getEventTicket(Long eventId, Long ticketTypeId) {
    return findByEventTicketInventory_EventIdAndTicketType_Id(eventId, ticketTypeId)
        .orElseThrow(()-> new ApplicationException(
            String.format("EventTicket for event : %s not found", eventId),
            ErrorCode.TICKET_EVENT_NOT_FOUND));
  }

  Optional<EventTicket> findByEventTicketInventory_EventIdAndTicketType_Id(@NonNull Long eventId, @NonNull Long id);
}
