package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.paymentservice.PaymentClient;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentBusinessService {
    private final PaymentClient paymentClient;

    //this api will be called by the bank when payment is complete
    public BaseAPIResponse<PaymentDTO> complete(Long paymentId) {
        return paymentClient.complete(paymentId);
    }
}
