package com.rakbank.busra.app.eventmgmt.dtos.requests;

import com.rakbank.busra.app.eventmgmt.dtos.commons.TicketBookingCapacity;
import lombok.Data;

import java.util.List;

@Data
public class EventCreateBusinessRequest {

    String name;
    String description;
    String location;
    String host;

    String dateTo;
    String dateFrom;

    List<TicketBookingCapacity> ticketBookingCapacities;
}
