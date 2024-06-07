package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.clients.paymentservice.dtos.commons.PaymentDTO;
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

  @PutMapping("/complete/{paymentId}")
  public BaseAPIResponse<PaymentDTO> complete(Long paymentId) {
    return service.complete(paymentId);
  }

}