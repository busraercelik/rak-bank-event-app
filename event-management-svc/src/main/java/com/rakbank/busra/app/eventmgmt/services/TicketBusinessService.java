package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.EventClient;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.NotificationClient;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.PaymentClient;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.TicketClient;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketTypeDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleResponseDTO;
import com.rakbank.busra.app.eventmgmt.clients.userservice.UserClient;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.dtos.requests.BookTicketBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.requests.CancelTicketBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.responses.BookTicketBusinessResponse;
import com.rakbank.busra.app.eventmgmt.dtos.responses.CancelTicketBusinessResponse;
import com.rakbank.busra.app.eventmgmt.dtos.responses.TicketView;
import com.rakbank.busra.app.eventmgmt.mappers.BusinessRequestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rakbank.busra.app.eventmgmt.services.NotificationMessageService.getBookingCancelledNotification;
import static com.rakbank.busra.app.eventmgmt.services.NotificationMessageService.getBookingSuccessfulNotification;

@Slf4j
@Service
@AllArgsConstructor
public class TicketBusinessService {

    private final UserClient userClient;
    private final EventClient eventClient;
    private final TicketClient ticketClient;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;
    private final BusinessRequestMapper businessRequestMapper;

    public BookTicketBusinessResponse bookTicket(BookTicketBusinessRequest request) {
        //fetch the user - validate if it exists
        var user = userClient.getById(request.getUserId()).getResult();

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

        //notify user of booking
        var notification = getBookingSuccessfulNotification(user, event, ticketSaleResponse);
        notificationClient.notifyUser(notification);
        return new BookTicketBusinessResponse(
                request.getUserId(), request.getEventId(), paymentResponse.getId(), ticketSaleResponse.getReferenceId());
    }


    public CancelTicketBusinessResponse cancelTicket(CancelTicketBusinessRequest request) {
        var user = userClient.getById(request.getUserId()).getResult();
        var ticket = ticketClient.fetchTicketByReferenceId(request.getReferenceId()).getResult();
        var cancelledTicket = ticketClient.cancelTicketSale(ticket.getReferenceId()).getResult();
        var refundedPayment = paymentClient.refund(ticket.getPaymentId()).getResult();

        var event = eventClient.getById(cancelledTicket.getEventId()).getResult();
        var cancellationNotification = getBookingCancelledNotification(user, event, refundedPayment, cancelledTicket);
        notificationClient.notifyUser(cancellationNotification);
        return new CancelTicketBusinessResponse(refundedPayment, cancelledTicket);
    }

    private static PaymentDTO getPaymentDTO(
            BookTicketBusinessRequest request, TicketSaleResponseDTO ticketSaleResponse, TicketTypeDTO ticketType) {
        var paymentRequest = new PaymentDTO();
        paymentRequest.setUserId(request.getUserId());
        paymentRequest.setEventId(request.getEventId());

        paymentRequest.setReferenceId(ticketSaleResponse.getReferenceId());
        paymentRequest.setTicketSaleId(ticketSaleResponse.getId());

        paymentRequest.setAmount(ticketType.getAmount());
        paymentRequest.setCurrency(ticketType.getCurrency());
        paymentRequest.setPaymentType(request.getPaymentType());
        return paymentRequest;
    }

    public List<TicketTypeDTO> fetchTicketTypes() {
        return ticketClient.getAllTicketTypes().getResult();
    }

    public TicketView getTicketByReferenceId(String referenceId) {

        var ticketSale = ticketClient.fetchTicketByReferenceId(referenceId).getResult();
        var event = eventClient.getById(ticketSale.getEventId()).getResult();
        var user = userClient.getById(ticketSale.getUserId()).getResult();
        var payment = paymentClient.getById(ticketSale.getPaymentId()).getResult();


        var ticketView = new TicketView();
        ticketView.setName(user.getName());
        ticketView.setEmail(user.getEmail());
        ticketView.setMobile(user.getPhone());

        ticketView.setReferenceId(ticketSale.getReferenceId());
        ticketView.setTicketType(ticketSale.getTicketTypeName());
        ticketView.setStatus(ticketSale.getTicketStatus());

        ticketView.setEventName(event.getName());
        ticketView.setEventLocation(event.getLocation());
        ticketView.setEventHost(event.getHost());
        ticketView.setEventTiming(event.getDateFrom() + " to "+ event.getDateTo());

        ticketView.setAmount(payment.getAmount());
        ticketView.setCurrency(payment.getCurrency());
        ticketView.setPaymentStatus(payment.getPaymentStatus());

        return ticketView;
    }
}
