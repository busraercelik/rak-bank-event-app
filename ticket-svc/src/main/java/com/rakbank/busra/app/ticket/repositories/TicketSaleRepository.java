package com.rakbank.busra.app.ticket.repositories;

import com.rakbank.busra.app.ticket.common.exceptions.ApplicationException;
import com.rakbank.busra.app.ticket.errors.ErrorCode;
import com.rakbank.busra.app.ticket.models.TicketSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TicketSaleRepository extends JpaRepository<TicketSale, Long> {

    Optional<TicketSale> findByReferenceIdIgnoreCase(@NonNull String referenceId);

    default TicketSale getByReferenceIdIgnoreCase(@NonNull String referenceId){
        return findByReferenceIdIgnoreCase(referenceId)
                .orElseThrow(()-> new ApplicationException(
                        String.format("TicketSale with id : %s not found", referenceId),
                        ErrorCode.TICKET_SALE_NOT_FOUND));
    }
}
