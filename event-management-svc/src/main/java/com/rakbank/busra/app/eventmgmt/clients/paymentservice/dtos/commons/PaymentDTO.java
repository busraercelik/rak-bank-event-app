package com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.Currency;
import com.rakbank.busra.app.eventmgmt.dtos.commons.PaymentStatus;
import com.rakbank.busra.app.eventmgmt.dtos.commons.PaymentType;
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
    String referenceId;

    BigDecimal amount;
    Currency currency;

    PaymentType paymentType;
    PaymentStatus paymentStatus;

    LocalDateTime paymentCreatedTime;
    LocalDateTime paymentCompletedTime;
    LocalDateTime paymentRefundedTime;

}
