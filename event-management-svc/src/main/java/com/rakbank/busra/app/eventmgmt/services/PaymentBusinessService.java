package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.paymentservice.PaymentClient;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.dtos.requests.PaymentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentBusinessService {
    private final PaymentClient paymentClient;

    public BaseAPIResponse<PaymentDTO> create(PaymentDTO dto) {
        return paymentClient.create(dto);
    }

}
