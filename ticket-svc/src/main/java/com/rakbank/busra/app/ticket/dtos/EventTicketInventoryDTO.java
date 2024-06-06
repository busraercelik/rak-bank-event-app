package com.rakbank.busra.app.ticket.dtos;


import lombok.Data;

import java.util.List;

@Data
public class EventTicketInventoryDTO {
    Long eventId;
    List<TicketBookingCapacity> ticketBookingCapacities;

    @Data
    public static class TicketBookingCapacity{
        String ticketTypeName;
        int totalTickets;
    }
}
