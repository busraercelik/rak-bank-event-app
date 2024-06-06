package com.rakbank.busra.app.user.services;

import com.rakbank.busra.app.user.exceptions.EventNotFoundException;
import com.rakbank.busra.app.user.models.Event;
import com.rakbank.busra.app.user.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }

    public List<Event> search(String search) {
        return eventRepository.searchText(search);
    }
}
