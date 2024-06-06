package com.rakbank.busra.app.eventmgmt.controllers;

import java.util.List;

import com.rakbank.busra.app.eventmgmt.dtos.EventCreateRequestDTO;
import com.rakbank.busra.app.eventmgmt.services.EventBusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/event")
class EventManagementController {

  private final EventBusinessService eventBusinessService;

  @PostMapping
  EventCreateRequestDTO create(EventCreateRequestDTO eventCreateRequestDTO) {
    return eventBusinessService.create(eventCreateRequestDTO);
  }

  @GetMapping("/{id}")
  EventCreateRequestDTO getById(@PathVariable String id) {
    return eventBusinessService.fetch(id);
  }

  @GetMapping
  List<EventCreateRequestDTO> search(@RequestParam("search") String search) {
    return eventBusinessService.search(search);
  }

}