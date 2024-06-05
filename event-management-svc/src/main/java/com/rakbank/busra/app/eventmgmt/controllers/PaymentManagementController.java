package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.dtos.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/payment")
class PaymentManagementController {

  @PostMapping
  PaymentDTO create(PaymentDTO dto) {
    return dto;
  }

  @PutMapping
  PaymentDTO update(PaymentDTO dto) {
    return dto;
  }
}