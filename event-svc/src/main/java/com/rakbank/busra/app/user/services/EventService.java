package com.rakbank.busra.app.user.services;

import com.rakbank.busra.app.user.common.exceptions.ApplicationException;
import com.rakbank.busra.app.user.dtos.EventDTO;
import com.rakbank.busra.app.user.errors.ErrorCode;
import com.rakbank.busra.app.user.mappers.EventMapper;
import com.rakbank.busra.app.user.models.Event;
import com.rakbank.busra.app.user.repositories.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EventService {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public Event create(EventDTO dto) {
        var event = getEvent(dto);
        eventRepository.saveAndFlush(event);
        return event;
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        String.format("No event found with id %s", id), ErrorCode.EVENT_NOT_FOUND));
    }

    public List<Event> search(String search) {
        return eventRepository.searchText(search);
    }

    private Event getEvent(EventDTO dto) {
        var result = getGetEventDates(dto);
        var event = eventMapper.toEventEntity(dto);
        event.setDateFrom(result.fromDate());
        event.setDateTo(result.toDate());
        return event;
    }

    private static EventDates getGetEventDates(EventDTO dto) {
        try {
            var fmt = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            var toDate = LocalDateTime.parse(dto.getDateTo(), fmt);
            var fromDate = LocalDateTime.parse(dto.getDateFrom(), fmt);
            return new EventDates(toDate, fromDate);
        } catch (Exception ex){
            throw new ApplicationException(String.format(
                    "dateFrom/dateTo should be in %s", DATE_TIME_FORMAT), ErrorCode.INVALID_DATE_TIME_FORMAT);
        }
    }

    public Event updateWithInventoryId(Long eventId, Long eventTicketInventoryId) {
        var event = getById(eventId);
        event.setEventTicketInventoryId(eventTicketInventoryId);
        eventRepository.saveAndFlush(event);
        return event;
    }

    private record EventDates(LocalDateTime toDate, LocalDateTime fromDate) {
    }

}
