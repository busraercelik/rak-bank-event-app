package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.EventClient;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.PaymentClient;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.TicketClient;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketTypeDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleResponseDTO;
import com.rakbank.busra.app.eventmgmt.dtos.requests.BookTicketBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.responses.BookTicketBusinessResponse;
import com.rakbank.busra.app.eventmgmt.mappers.BusinessRequestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TicketBusinessService {

    private final EventClient eventClient;
    private final TicketClient ticketClient;
    private final PaymentClient paymentClient;
    private final BusinessRequestMapper businessRequestMapper;

    public BookTicketBusinessResponse bookTicket(BookTicketBusinessRequest request) {
        // fetch the event - validate if it exists
        var event = eventClient.getById(request.getEventId()).getResult();
        log.info("Booking ticket for event : {} for user : {}", event.getName(), request.getUserId());
        //check for valid ticket type
        var ticketType = ticketClient.getTicketType(request.getTicketTypeName()).getResult();
        var ticketBookRequest = businessRequestMapper.toTicketSaleRequestDTO(ticketType, request);
        //create a ticket sale record
        var ticketSaleResponse = ticketClient.createTicketSale(ticketBookRequest).getResult();
        //create a payment record for the ticket sale
        var paymentRequest = getPaymentDTO(request, ticketSaleResponse, ticketType);
        var paymentResponse = paymentClient.create(paymentRequest).getResult();
        //link payment with ticketSale
        ticketClient.linkPaymentWithTicketSale(ticketSaleResponse.getReferenceId(), paymentResponse.getId());

        return new BookTicketBusinessResponse(
                request.getUserId(), request.getEventId(), paymentResponse.getId(), ticketSaleResponse.getReferenceId());
    }

    private static PaymentDTO getPaymentDTO(BookTicketBusinessRequest request, TicketSaleResponseDTO ticketSaleResponse, TicketTypeDTO ticketType) {
        var paymentRequest = new PaymentDTO();
        paymentRequest.setUserId(request.getUserId());
        paymentRequest.setEventId(request.getEventId());
        paymentRequest.setTicketSaleId(ticketSaleResponse.getId());

        paymentRequest.setAmount(ticketType.getAmount());
        paymentRequest.setCurrency(ticketType.getCurrency());
        paymentRequest.setPaymentType(request.getPaymentType());
        return paymentRequest;
    }
}
