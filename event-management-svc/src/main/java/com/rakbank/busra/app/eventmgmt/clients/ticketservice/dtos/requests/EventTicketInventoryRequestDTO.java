package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.requests;


import com.rakbank.busra.app.eventmgmt.dtos.commons.TicketBookingCapacity;
import lombok.Data;

import java.util.List;

@Data
public class EventTicketInventoryRequestDTO {
    Long eventId;
    List<TicketBookingCapacity> ticketBookingCapacities;
}
