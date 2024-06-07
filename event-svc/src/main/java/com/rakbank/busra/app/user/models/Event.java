package com.rakbank.busra.app.user.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

import static com.rakbank.busra.app.user.services.EventService.DATE_TIME_FORMAT;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    String description;
    String location;
    String host;
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    LocalDateTime dateFrom;
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    LocalDateTime dateTo;
    Long eventTicketInventoryId;
}
