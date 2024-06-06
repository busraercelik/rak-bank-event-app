package com.rakbank.busra.app.ticket.repositories;


import com.rakbank.busra.app.ticket.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    Optional<TicketType> findByTicketTypeNameIgnoreCase(@NonNull String ticketTypeName);
}
