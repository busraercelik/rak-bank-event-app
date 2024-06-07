package com.rakbank.busra.app.eventmgmt.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookTicketBusinessResponse {
    Long userId;
    Long eventId;
    Long paymentId;
    String ticketReferenceId;
}
