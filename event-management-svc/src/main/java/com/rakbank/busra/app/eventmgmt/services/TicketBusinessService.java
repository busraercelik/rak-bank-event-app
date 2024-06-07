package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.TicketClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TicketBusinessService {
    private final TicketClient ticketClient;

}
