package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.PaymentClient;
import com.rakbank.busra.app.eventmgmt.dtos.PaymentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentBusinessService {
    private final PaymentClient paymentClient;

    public PaymentDTO create(PaymentDTO dto) {
        return paymentClient.create(dto);
    }

    public PaymentDTO update(PaymentDTO dto) {
        return paymentClient.update(dto);
    }
}
