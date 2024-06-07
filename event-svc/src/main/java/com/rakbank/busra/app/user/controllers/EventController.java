package com.rakbank.busra.app.user.controllers;

import com.rakbank.busra.app.user.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.user.dtos.EventDTO;
import com.rakbank.busra.app.user.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    BaseAPIResponse<EventDTO> create(EventDTO dto) {
        var result = eventService.create(dto);
        return new BaseAPIResponse<>("200","event created successfully", result);
    }

    @GetMapping(path = "/{id}")
    BaseAPIResponse<EventDTO> getById(@PathVariable("id") Long eventId) {
        var result = eventService.getById(eventId);
        return new BaseAPIResponse<>("200","event fetched successfully", result);
    }

    @GetMapping
    BaseAPIResponse<List<EventDTO>> search(@RequestParam("search") String search) {
        var result = eventService.search(search);
        return new BaseAPIResponse<>("200","event searched successfully", result);
    }

}
