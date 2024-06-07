package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.requests;


import lombok.Data;

import java.util.List;

@Data
public class EventTicketInventoryRequestDTO {
    Long eventId;
    List<TicketBookingCapacity> ticketBookingCapacities;

    @Data
    public static class TicketBookingCapacity{
        String ticketTypeName;
        int totalTickets;
    }
}
