package com.rakbank.busra.app.payment.controllers;

import com.rakbank.busra.app.payment.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.payment.dtos.PaymentDTO;
import com.rakbank.busra.app.payment.models.Payment;
import com.rakbank.busra.app.payment.services.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    BaseAPIResponse<Payment> create(@Valid @RequestBody PaymentDTO dto) {
        var result = paymentService.create(dto);
        return new BaseAPIResponse<>("200", "Payment is created successfully", result);
    }

    @GetMapping("/{paymentId}")
    BaseAPIResponse<Payment> getPayment(@PathVariable("paymentId") Long paymentId) {
        var result = paymentService.getById(paymentId);
        return new BaseAPIResponse<>("200", "Payment fetched successfully", result);
    }

    @PutMapping("/complete/{paymentId}")
    BaseAPIResponse<Payment> complete(@PathVariable("paymentId") Long paymentId) {
        var result = paymentService.completePayment(paymentId);
        return new BaseAPIResponse<>("200", "Payment is updated to complete successfully", result);
    }

    @PutMapping("/refund/{paymentId}")
    BaseAPIResponse<Payment> refund(@PathVariable("paymentId") Long paymentId) {
        var result = paymentService.refundPayment(paymentId);
        return new BaseAPIResponse<>("200", "Payment is updated to cancelled successfully", result);
    }
}
