package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.dtos.requests.BookTicketBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.responses.BookTicketBusinessResponse;
import com.rakbank.busra.app.eventmgmt.dtos.responses.EventCreateBusinessResponse;
import com.rakbank.busra.app.eventmgmt.services.TicketBusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/ticket")
class TicketManagementController {
    private TicketBusinessService businessService;

    @PostMapping("/book")
    BaseAPIResponse<BookTicketBusinessResponse> bookTicket(@RequestBody BookTicketBusinessRequest request) {
        var result = businessService.bookTicket(request);
        return new BaseAPIResponse<>("200", "booked a ticket to event", result);
    }


}