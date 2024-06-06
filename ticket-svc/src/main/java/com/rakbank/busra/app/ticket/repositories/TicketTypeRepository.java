package com.rakbank.busra.app.ticket.repositories;


import com.rakbank.busra.app.ticket.common.exceptions.ApplicationException;
import com.rakbank.busra.app.ticket.errors.ErrorCode;
import com.rakbank.busra.app.ticket.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    Optional<TicketType> findByTicketTypeNameIgnoreCase(@NonNull String ticketTypeName);

    default TicketType getByTicketTypeNameIgnoreCase(@NonNull String ticketTypeName){
        return findByTicketTypeNameIgnoreCase(ticketTypeName)
                .orElseThrow(()-> new ApplicationException(
                        String.format("Ticket Type with name : %s not found", ticketTypeName),
                        ErrorCode.TICKET_TYPE_NOT_FOUND));
    }
}
