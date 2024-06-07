package com.rakbank.busra.app.eventmgmt.controllers;

import java.util.List;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.dtos.requests.EventCreateBusinessRequest;
import com.rakbank.busra.app.eventmgmt.dtos.responses.EventCreateBusinessResponse;
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
  BaseAPIResponse<EventCreateBusinessResponse> create(@RequestBody EventCreateBusinessRequest request) {
    var result = eventBusinessService.create(request);
    return new BaseAPIResponse<>("200", "created a new event", result);
  }

  @GetMapping("/{id}")
  BaseAPIResponse<EventDTO> getById(@PathVariable Long id) {
    return eventBusinessService.fetch(id);
  }

  @GetMapping
  BaseAPIResponse<List<EventDTO>> search(@RequestParam("search") String search) {
    return eventBusinessService.search(search);
  }

}