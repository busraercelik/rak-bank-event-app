package com.rakbank.busra.app.payment.dtos;

import com.rakbank.busra.app.payment.models.PaymentStatus;
import com.rakbank.busra.app.payment.models.PaymentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDTO {
    Long userId;
    BigDecimal amount;
    PaymentStatus paymentStatus;
    LocalDateTime paymentTime;
    PaymentType paymentType;
}