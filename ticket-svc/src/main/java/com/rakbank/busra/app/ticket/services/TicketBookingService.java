package com.rakbank.busra.app.ticket.services;

import com.rakbank.busra.app.ticket.dtos.TicketSaleDTO;
import com.rakbank.busra.app.ticket.repositories.EventTicketInventoryRepository;
import com.rakbank.busra.app.ticket.repositories.EventTicketRepository;
import com.rakbank.busra.app.ticket.repositories.TicketTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class TicketBookingService {

    private final TicketTypeRepository ticketTypeRepository;
    private final EventTicketRepository eventTicketRepository;
    private final EventTicketInventoryRepository eventTicketInventoryRepository;

    public TicketSaleDTO book(TicketSaleDTO dto) {
        return null;
    }

    public TicketSaleDTO cancel(String ticketSaleId) {
        return null;
    }
}
