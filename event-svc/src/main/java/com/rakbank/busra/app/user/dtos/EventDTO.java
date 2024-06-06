package com.rakbank.busra.app.user.dtos;

import lombok.Data;

@Data
public class EventDTO {
    Long id;

    String name;
    String description;
    String host;

    String dateTo;
    String dateFrom;
}
