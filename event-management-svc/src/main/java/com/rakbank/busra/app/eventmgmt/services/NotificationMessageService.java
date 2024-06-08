package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.NotificationType;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.requests.NotificationRequestDTO;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleResponseDTO;
import com.rakbank.busra.app.eventmgmt.clients.userservice.dtos.commons.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationMessageService {


    static NotificationRequestDTO getPaymentCompletedNotification(UserDTO user, PaymentDTO payment) {
        var request = new NotificationRequestDTO();
        request.setRecipient(user.getEmail());
        request.setSubject(String.format("Payment complete - %s", payment.getReferenceId()));
        request.setNotificationType(NotificationType.EMAIL);
        request.setMessage(String.format("""
                You have successfully completed a payment.
                Amount : %s
                ReferenceId : %s
                """, payment.getAmount(), payment.getReferenceId()));
        return request;
    }

    static NotificationRequestDTO getBookingCancelledNotification(
            UserDTO user, EventDTO event, PaymentDTO refundedPayment, TicketSaleDTO cancelledTicket) {
        var notification = new NotificationRequestDTO();
        notification.setNotificationType(NotificationType.EMAIL);
        notification.setRecipient(user.getEmail());
        notification.setSubject(String.format("Booking for event %s cancelled successfully", event.getName()));
        notification.setMessage(String.format("""
                Your booking for event %s on date %s is cancelled successfully
                
                ● Ticket ReferenceId : %s
                ● Refund Amount : %s
                """, event.getName(), event.getDateFrom(), cancelledTicket.getReferenceId(), refundedPayment.getAmount()));
        return notification;
    }

    static NotificationRequestDTO getBookingSuccessfulNotification(UserDTO user, EventDTO event, TicketSaleResponseDTO ticketSaleResponse) {
        var notification = new NotificationRequestDTO();
        notification.setNotificationType(NotificationType.EMAIL);
        notification.setRecipient(user.getEmail());
        notification.setSubject(String.format("Booking for event %s on date %s successful",
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
                event.getLocation(), user.getEmail(), ticketSaleResponse.getTicketTypeName(), ticketSaleResponse.getAmount()));
        return notification;
    }


}
