package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.commons.TicketTypeDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.TicketSaleResponseDTO;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.dtos.requests.BookTicketBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.requests.CancelTicketBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.responses.BookTicketBusinessResponse;
import com.rakbank.busra.app.eventmgmt.dtos.responses.CancelTicketBusinessResponse;
import com.rakbank.busra.app.eventmgmt.dtos.responses.TicketView;
import com.rakbank.busra.app.eventmgmt.services.TicketBusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/ticket")
class TicketManagementController {
    private TicketBusinessService businessService;

    @GetMapping("/types")
    BaseAPIResponse<List<TicketTypeDTO>> fetchAllTicketTypes(){
        var result = businessService.fetchTicketTypes();
        return new BaseAPIResponse<>("200", "fetched all ticket types", result);
    }

    @GetMapping("/{referenceId}")
    BaseAPIResponse<TicketView> fetchTicket(@PathVariable("referenceId") String referenceId) {
        var result = businessService.getTicketByReferenceId(referenceId);
        return new BaseAPIResponse<>("200", "fetched ticket successfully", result);
    }

    @PostMapping("/book")
    BaseAPIResponse<BookTicketBusinessResponse> bookTicket(@RequestBody BookTicketBusinessRequest request) {
        var result = businessService.bookTicket(request);
        return new BaseAPIResponse<>("200", "booked a ticket to event", result);
    }

    @PostMapping("/cancel")
    BaseAPIResponse<CancelTicketBusinessResponse> cancelTicket(@RequestBody CancelTicketBusinessRequest request) {
        var result = businessService.cancelTicket(request);
        return new BaseAPIResponse<>("200", "cancelled ticket to event", result);
    }

}