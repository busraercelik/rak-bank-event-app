package com.rakbank.busra.app.user.controllers;

import com.rakbank.busra.app.user.models.Event;
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
    Event create(Event event) {
        return eventService.create(event);
    }

    @GetMapping(path = "/{id}")
    public Event getById(@PathVariable("id") Long eventId) {
        return eventService.getById(eventId);
    }

    @GetMapping
    List<Event> search(@RequestParam("search") String search) {
        return eventService.search(search);
    }

}
