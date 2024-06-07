package com.rakbank.busra.app.eventmgmt.dtos.requests;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dto.commons.Currency;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDTO {
    Long id;

    Long userId;
    Long eventId;
    Long ticketSaleId;

    BigDecimal amount;
    Currency currency;

    PaymentType paymentType;
    PaymentStatus paymentStatus;

    LocalDateTime paymentCreatedTime;
    LocalDateTime paymentCompletedTime;
    LocalDateTime paymentCancelledTime;

    enum PaymentStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }

    enum PaymentType {
        CREDIT_CARD, CASH, VOUCHER
    }
}