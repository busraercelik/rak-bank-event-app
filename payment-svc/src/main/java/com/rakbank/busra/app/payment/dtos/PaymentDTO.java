package com.rakbank.busra.app.payment.dtos;

import com.rakbank.busra.app.payment.models.Currency;
import com.rakbank.busra.app.payment.models.PaymentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDTO {
    Long userId;
    Long eventId;
    Long ticketSaleId;
    String referenceId;

    BigDecimal amount;
    Currency currency;

    PaymentType paymentType;
}