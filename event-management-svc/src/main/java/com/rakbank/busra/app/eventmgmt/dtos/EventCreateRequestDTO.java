package com.rakbank.busra.app.eventmgmt.dtos;

import java.time.LocalDateTime;

public class EventCreateRequestDTO {
    String name;
    String description;
    String host;
    LocalDateTime dateFrom;
    LocalDateTime dateTo;
}
