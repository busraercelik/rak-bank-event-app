package com.rakbank.busra.app.eventmgmt.dtos.responses;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.Currency;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketStatus;
import com.rakbank.busra.app.eventmgmt.dtos.commons.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketView {
  String name;
  String email;
  String mobile;

  String referenceId;
  String ticketType;
  TicketStatus status;

  String eventName;
  String eventLocation;
  String eventHost;
  String eventTiming;

  BigDecimal amount;
  Currency currency;
  PaymentStatus paymentStatus;
}
