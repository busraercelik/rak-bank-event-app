package com.rakbank.busra.app.eventmgmt.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDTO {
    BigDecimal amount;
    PaymentStatus paymentStatus;
    LocalDateTime paymentCreatedTime;
    PaymentType paymentType;

    enum PaymentStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }

    enum PaymentType {
        CREDIT_CARD, CASH, VOUCHER
    }
}
