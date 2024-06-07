package com.rakbank.busra.app.payment.services;

import com.rakbank.busra.app.payment.dtos.PaymentDTO;
import com.rakbank.busra.app.payment.mappers.PaymentMapper;
import com.rakbank.busra.app.payment.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Transactional
@Service
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper paymentMapper;

    public PaymentDTO create(PaymentDTO dto) {
        return null;
//        return repository.saveAndFlush(dto);
    }

    public PaymentDTO update(PaymentDTO dto) {
        return null;
//        return repository.update(dto);
    }
}
