package com.rakbank.busra.app.ticket.controllers;

import com.rakbank.busra.app.ticket.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.ticket.dtos.CreateTicketEventInventoryResultDTO;
import com.rakbank.busra.app.ticket.dtos.EventTicketInventoryDTO;
import com.rakbank.busra.app.ticket.models.EventTicketInventory;
import com.rakbank.busra.app.ticket.models.TicketType;
import com.rakbank.busra.app.ticket.services.TicketInventoryManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/ticket")
class TicketEventInventoryController {
    private final TicketInventoryManagementService ticketInventoryManagementService;

    @PostMapping("/inventory")
    BaseAPIResponse<CreateTicketEventInventoryResultDTO> create(@RequestBody EventTicketInventoryDTO dto) {
        var result = ticketInventoryManagementService.createTicketEventInventory(dto);
        var detail = result.isCreated()? "Event ticket inventory created successfully": "Event ticket inventory already exists";
        return new BaseAPIResponse<>("200", detail, result);
    }

    @GetMapping(path = "/event/{eventId}")
    BaseAPIResponse<EventTicketInventory> getEventTicketInventoryByEventId(@PathVariable("eventId") Long eventId) {
        var inventory = ticketInventoryManagementService.getEventTicketInventoryByEventId(eventId);
        return new BaseAPIResponse<>("200", "Event ticket inventory fetched successfully.", inventory);
    }

    @GetMapping(path = "/type")
    BaseAPIResponse<List<TicketType>> getAllTicketTypes() {
        var ticketTypes = ticketInventoryManagementService.getAllTicketTypes();
        return new BaseAPIResponse<>("200", "Ticket types fetched successfully.", ticketTypes);
    }

}