package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.EventClient;
import com.rakbank.busra.app.eventmgmt.dtos.EventCreateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventBusinessService {
    private final EventClient eventClient;

    public EventCreateRequestDTO create(EventCreateRequestDTO dto) {
        return eventClient.createEvent(dto);
    }

    public EventCreateRequestDTO fetch(String id) {
        return eventClient.getById(id);
    }

    public List<EventCreateRequestDTO> search(String search) {
        return eventClient.search(search);
    }
}
