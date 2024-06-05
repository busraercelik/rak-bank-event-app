package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.dtos.TicketDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/ticket")
class TicketManagementController {

  @PostMapping
  TicketDTO create(TicketDTO dto) {
    return dto;
  }

  @PutMapping
  TicketDTO cancel(TicketDTO dto) {
    return dto;
  }

}