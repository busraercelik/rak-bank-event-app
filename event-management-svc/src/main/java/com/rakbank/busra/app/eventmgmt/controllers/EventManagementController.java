package com.rakbank.busra.app.eventmgmt.controllers;

import java.util.List;

import com.rakbank.busra.app.eventmgmt.dtos.EventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/event")
class EventManagementController {

  @PostMapping
  EventDTO create(EventDTO eventDTO) {
    return eventDTO;
  }

  @GetMapping("/{id}")
  EventDTO getById(@PathVariable String id) {
    return null;
  }

  @GetMapping
  List<EventDTO> search(@RequestParam("search") String search) {
    return List.of();
  }

}