package com.rakbank.busra.app.user.services;

import com.rakbank.busra.app.user.dtos.EventDTO;
import com.rakbank.busra.app.user.common.exceptions.ApplicationException;
import com.rakbank.busra.app.user.errors.ErrorCode;
import com.rakbank.busra.app.user.mappers.EventMapper;
import com.rakbank.busra.app.user.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventDTO create(EventDTO dto) {
        var savedEvent = eventRepository.save(eventMapper.eventCreateRequestDTOToEvent(dto));
        return eventMapper.eventToEventResponseDTO(savedEvent);
    }

    public EventDTO getById(Long id) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        String.format("No event found with id %s", id), ErrorCode.EVENT_NOT_FOUND));
        return eventMapper.eventToEventResponseDTO(event);
    }

    public List<EventDTO> search(String search) {
        return eventRepository.searchText(search)
                .stream().map(eventMapper::eventToEventResponseDTO).toList();
    }
}
