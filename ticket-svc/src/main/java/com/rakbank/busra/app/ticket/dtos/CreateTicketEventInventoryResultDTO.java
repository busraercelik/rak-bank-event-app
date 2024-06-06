package com.rakbank.busra.app.ticket.dtos;

import com.rakbank.busra.app.ticket.models.EventTicketInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTicketEventInventoryResultDTO {
    boolean created;
    EventTicketInventory inventory;
}
