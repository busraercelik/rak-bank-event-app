package com.rakbank.busra.app.eventmgmt.dtos.responses;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventResponseDTO {
    String id;
    String name;
    String description;
    String host;

    String dateTo;
    String dateFrom;
}
