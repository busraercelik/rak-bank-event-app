package com.rakbank.busra.app.user.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EventDTO {
    String name;
    String description;
    String host;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime dateTo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime dateFrom;
}
