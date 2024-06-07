package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.notificationservice.NotificationClient;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.NotificationType;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.requests.NotificationRequestDTO;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.PaymentClient;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
import com.rakbank.busra.app.eventmgmt.clients.userservice.UserClient;
import com.rakbank.busra.app.eventmgmt.clients.userservice.dtos.commons.UserDTO;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentBusinessService {

    private final UserClient userClient;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;

    //this api will be called by the bank when payment is complete
    public BaseAPIResponse<PaymentDTO> complete(Long paymentId) {
        var paymentResponse = paymentClient.complete(paymentId);
        var payment = paymentResponse.getResult();

        var userId = payment.getUserId();
        var user = userClient.getById(userId).getResult();

        var notification = getPaymentCompletedNotification(user, payment);
        notificationClient.notifyUser(notification);
        return paymentResponse;
    }

    private static NotificationRequestDTO getPaymentCompletedNotification(UserDTO user, PaymentDTO payment) {
        var request = new NotificationRequestDTO();
        request.setRecipient(user.getEmail());
        request.setNotificationType(NotificationType.EMAIL);
        request.setMessage(String.format("""
                You have successfully completed a payment.
                Amount : %s
                ReferenceId : %s
                """, payment.getAmount(), payment.getReferenceId()));
        return request;
    }
}
