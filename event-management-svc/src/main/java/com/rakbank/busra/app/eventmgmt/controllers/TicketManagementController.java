package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.dtos.requests.TicketDTO;
import com.rakbank.busra.app.eventmgmt.services.TicketBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/ticket")
class TicketManagementController {
    private TicketBusinessService businessService;

    @PostMapping
    TicketDTO create(TicketDTO dto) {
        return businessService.create(dto);
    }

    @PutMapping
    TicketDTO cancel(String id) {
        return businessService.cancel(id);
    }

}