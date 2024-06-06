package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.dtos.PaymentDTO;
import com.rakbank.busra.app.eventmgmt.services.PaymentBusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/payment")
class PaymentManagementController {

  private final PaymentBusinessService service;

  @PostMapping
  PaymentDTO create(PaymentDTO dto) {
    return service.create(dto);
  }

  @PutMapping
  PaymentDTO update(PaymentDTO dto) {
    return service.update(dto);
  }
}