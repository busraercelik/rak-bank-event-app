package com.rakbank.busra.app.user.controllers;

import com.rakbank.busra.app.user.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.user.dtos.EventDTO;
import com.rakbank.busra.app.user.models.Event;
import com.rakbank.busra.app.user.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/event")
public class EventController {

    private final EventService eventService;

    @PostMapping
    BaseAPIResponse<Event> create(@RequestBody EventDTO dto) {
        var result = eventService.create(dto);
        return new BaseAPIResponse<>("200","event created successfully", result);
    }

    @GetMapping(path = "/{id}")
    BaseAPIResponse<Event> getById(@PathVariable("id") Long eventId) {
        var result = eventService.getById(eventId);
        return new BaseAPIResponse<>("200","event fetched successfully", result);
    }

    @PutMapping(path = "/link/{eventId}/{inventoryId}")
    BaseAPIResponse<Event> updateWithInventoryId(
            @PathVariable("eventId") Long eventId, @PathVariable("inventoryId") Long inventoryId) {
        var result = eventService.updateWithInventoryId(eventId, inventoryId);
        return new BaseAPIResponse<>("200","event fetched successfully", result);
    }

    @GetMapping
    BaseAPIResponse<List<Event>> search(@RequestParam("search") String search) {
        var result = eventService.search(search);
        return new BaseAPIResponse<>("200","event searched successfully", result);
    }

}
