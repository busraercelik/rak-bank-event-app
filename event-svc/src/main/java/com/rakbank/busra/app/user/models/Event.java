package com.rakbank.busra.app.user.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    String name;
    String description;
    String host;
    LocalDate dateFrom;
    LocalDate dateTo;
}
