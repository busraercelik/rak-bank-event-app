package com.rakbank.busra.app.ticket.controllers;

import com.rakbank.busra.app.ticket.dtos.TicketSaleDTO;
import com.rakbank.busra.app.ticket.services.TicketBookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/ticket/booking")
class TicketBookingController {
    private TicketBookingService ticketBookingService;

    @PostMapping
    TicketSaleDTO create(TicketSaleDTO dto) {
        return ticketBookingService.book(dto);
    }

    @PutMapping
    TicketSaleDTO cancel(String id) {
        return ticketBookingService.cancel(id);
    }

}