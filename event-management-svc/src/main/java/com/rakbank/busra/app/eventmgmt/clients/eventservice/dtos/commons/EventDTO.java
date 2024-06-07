package com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDTO {
    Long id;
    String name;
    String description;
    String location;
    String host;

    String dateTo;
    String dateFrom;
    Long eventTicketInventoryId;
}
