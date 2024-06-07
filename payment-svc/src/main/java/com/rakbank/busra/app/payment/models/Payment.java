package com.rakbank.busra.app.payment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    Long userId;
    BigDecimal amount;
    PaymentStatus paymentStatus;
    LocalDateTime paymentTime;
    PaymentType paymentType;
}
