package com.rakbank.busra.app.user.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class TicketType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    String name;
    BigDecimal price;
}
