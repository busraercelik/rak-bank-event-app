package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTicketEventInventoryResultDTO {
    boolean created;
    EventTicketInventoryResponseDTO inventory;
}
