package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTicketEventInventoryResultDTO {
    boolean created;
    EventTicketInventoryResponseDTO inventory;
}
