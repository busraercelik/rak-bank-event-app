package com.rakbank.busra.app.user.dtos;

import lombok.Data;

@Data
public class EventDTO {
    String name;
    String description;
    String location;
    String host;

    String dateTo;
    String dateFrom;
}
