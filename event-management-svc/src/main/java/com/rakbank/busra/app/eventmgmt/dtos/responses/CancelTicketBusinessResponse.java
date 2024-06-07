package com.rakbank.busra.app.eventmgmt.dtos.responses;

import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelTicketBusinessResponse {
    PaymentDTO refundedPayment;
    TicketSaleDTO cancelledTicket;
}
