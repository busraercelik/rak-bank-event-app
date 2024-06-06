package com.rakbank.busra.app.ticket.controllers;

import com.rakbank.busra.app.ticket.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.ticket.dtos.TicketSaleRequestDTO;
import com.rakbank.busra.app.ticket.dtos.TicketSaleResponseDTO;
import com.rakbank.busra.app.ticket.models.TicketSale;
import com.rakbank.busra.app.ticket.services.TicketBookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/ticket/booking")
class TicketBookingController {
    private TicketBookingService ticketBookingService;

    @PostMapping
    BaseAPIResponse<TicketSaleResponseDTO> book(@Valid @RequestBody TicketSaleRequestDTO dto) {
        var result = ticketBookingService.book(dto);
        return new BaseAPIResponse<>("200",
                String.format("Ticket booked successfully, referenceId : %s", result.getReferenceId()), result);
    }

    @GetMapping("/{referenceId}")
    BaseAPIResponse<TicketSale> fetchTicketByReferenceId(@PathVariable("referenceId") String referenceId) {
        var result = ticketBookingService.fetchTicketByReferenceId(referenceId);
        return new BaseAPIResponse<>("200", "Ticket fetched successfully", result);
    }

    @PutMapping("/{referenceId}")
    BaseAPIResponse<TicketSale> cancel(@PathVariable("referenceId") String referenceId) {
        var result = ticketBookingService.cancel(referenceId);
        return new BaseAPIResponse<>("200", "Ticket status updated to CANCELLED successfully", result);
    }

}