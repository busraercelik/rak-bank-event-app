package com.rakbank.busra.app.user.dtos;

import lombok.Data;

@Data
public class EventDTO {
    String name;
    String description;
    String host;

    String dateTo;
    String dateFrom;
}
