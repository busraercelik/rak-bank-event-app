package com.rakbank.busra.app.eventmgmt.dtos.requests;

import com.rakbank.busra.app.eventmgmt.dtos.commons.PaymentType;
import lombok.Data;

@Data
public class BookTicketBusinessRequest {
    Long userId;
    Long eventId;
    String ticketTypeName;
    PaymentType paymentType;
}
