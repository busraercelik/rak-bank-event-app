package com.rakbank.busra.app.payment.controllers;

import com.rakbank.busra.app.payment.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.payment.dtos.PaymentDTO;
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
    BaseAPIResponse<PaymentDTO> create(@Valid @RequestBody PaymentDTO dto) {
        var result = paymentService.create(dto);
        return new BaseAPIResponse<>("200",
                String.format("Payment is done successfully, userId : %s", result.getUserId()), result);
    }

    @PutMapping
    BaseAPIResponse<PaymentDTO> update(@Valid @RequestBody PaymentDTO dto) {
        var result = paymentService.update(dto);
        return new BaseAPIResponse<>("200",
                String.format("Payment is updated successfully, userId : %s", result.getUserId()), result);
    }
}
