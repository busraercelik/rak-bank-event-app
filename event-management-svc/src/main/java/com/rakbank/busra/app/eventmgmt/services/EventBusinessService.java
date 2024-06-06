package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.EventClient;
import com.rakbank.busra.app.eventmgmt.dtos.EventDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventBusinessService {
    private final EventClient eventClient;

    public EventDTO create(EventDTO dto) {
        return eventClient.createEvent(dto);
    }

    public EventDTO fetch(String id) {
        return eventClient.getById(id);
    }

    public List<EventDTO> search(String search) {
        return eventClient.search(search);
    }
}
