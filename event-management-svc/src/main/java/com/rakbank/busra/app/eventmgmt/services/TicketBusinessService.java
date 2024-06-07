package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.TicketClient;
import com.rakbank.busra.app.eventmgmt.dtos.requests.TicketDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TicketBusinessService {
    private final TicketClient ticketClient;

    public TicketDTO create(TicketDTO dto) {
        return ticketClient.createTicketSale(dto);
    }
}
