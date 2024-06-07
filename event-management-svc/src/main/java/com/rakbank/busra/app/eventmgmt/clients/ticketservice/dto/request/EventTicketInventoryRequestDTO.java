package com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.request;


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
