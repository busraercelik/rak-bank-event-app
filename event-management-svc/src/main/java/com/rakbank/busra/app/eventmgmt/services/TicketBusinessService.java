package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.TicketClient;
import com.rakbank.busra.app.eventmgmt.dtos.TicketDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TicketBusinessService {
    private final TicketClient ticketClient;

    public TicketDTO create(TicketDTO dto) {
        return ticketClient.create(dto);
    }

    public TicketDTO cancel(String id) {
        return ticketClient.cancel(id);
    }
}
