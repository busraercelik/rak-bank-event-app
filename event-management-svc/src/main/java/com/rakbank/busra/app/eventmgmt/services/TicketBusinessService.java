package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.EventClient;
import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.NotificationClient;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.NotificationType;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.requests.NotificationRequestDTO;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.PaymentClient;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.TicketClient;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketTypeDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleResponseDTO;
import com.rakbank.busra.app.eventmgmt.clients.userservice.UserClient;
import com.rakbank.busra.app.eventmgmt.clients.userservice.dtos.commons.UserDTO;
import com.rakbank.busra.app.eventmgmt.dtos.requests.BookTicketBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.requests.CancelTicketBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.responses.BookTicketBusinessResponse;
import com.rakbank.busra.app.eventmgmt.dtos.responses.CancelTicketBusinessResponse;
import com.rakbank.busra.app.eventmgmt.mappers.BusinessRequestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    private static NotificationRequestDTO getBookingSuccessfulNotification(UserDTO user, EventDTO event, TicketSaleResponseDTO ticketSaleResponse) {
        var notification = new NotificationRequestDTO();
        notification.setNotificationType(NotificationType.EMAIL);
        notification.setRecipient(user.getEmail());
        notification.setSubject(String.format("Event %s booked successfully for date %s",
                event.getName(), event.getDateFrom()));
        notification.setMessage(String.format("""
                You have successfully booked a ticket - %s
                
                ● Event Name : %s
                ● Event Date : %s
                ● Event Location :%s
                ● User Name : %s
                ● Ticket Type : %s
                ● Number of Ticket(s) : 1
                ● Payment Amount : %s
                """, ticketSaleResponse.getReferenceId(), event.getName(), event.getDateFrom(),
                "", user.getEmail(), ticketSaleResponse.getTicketTypeName(), ticketSaleResponse.getAmount()));
        return notification;
    }


    public CancelTicketBusinessResponse cancelTicket(CancelTicketBusinessRequest request) {
        var ticket = ticketClient.fetchTicketByReferenceId(request.getReferenceId()).getResult();
        var cancelledTicket = ticketClient.cancelTicketSale(ticket.getReferenceId()).getResult();
        var refundedPayment = paymentClient.refund(ticket.getPaymentId()).getResult();
        return new CancelTicketBusinessResponse(refundedPayment, cancelledTicket);
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
