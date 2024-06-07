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
    Long eventId;

    Long ticketSaleId;
    String referenceId;

    BigDecimal amount;
    Currency currency;

    PaymentType paymentType;
    PaymentStatus paymentStatus;

    LocalDateTime paymentCreatedTime;
    LocalDateTime paymentCompletedTime;
    LocalDateTime paymentRefundedTime;
}
