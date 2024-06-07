package com.rakbank.busra.app.payment.services;

import com.rakbank.busra.app.payment.dtos.PaymentDTO;
import com.rakbank.busra.app.payment.mappers.PaymentMapper;
import com.rakbank.busra.app.payment.models.Payment;
import com.rakbank.busra.app.payment.models.PaymentStatus;
import com.rakbank.busra.app.payment.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Transactional
@Service
public class PaymentService {
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    public Payment create(PaymentDTO dto) {
        var payment = paymentMapper.toPaymentEntity(dto);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentCreatedTime(LocalDateTime.now());
        return paymentRepository.saveAndFlush(payment);
    }

    public Payment completePayment(Long paymentId) {
        var payment = paymentRepository.getByPaymentId(paymentId);
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment.setPaymentCompletedTime(LocalDateTime.now());
        paymentRepository.saveAndFlush(payment);
        return payment;
    }

    public Payment refundPayment(Long paymentId) {
        var payment = paymentRepository.getByPaymentId(paymentId);
        payment.setPaymentStatus(PaymentStatus.REFUNDED);
        payment.setPaymentRefundedTime(LocalDateTime.now());
        paymentRepository.saveAndFlush(payment);
        return payment;
    }
}
