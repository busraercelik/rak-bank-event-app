package com.rakbank.busra.app.eventmgmt.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCreateRequestDTO {
    String name;
    String description;
    String host;
    LocalDateTime dateFrom;
    LocalDateTime dateTo;
}
