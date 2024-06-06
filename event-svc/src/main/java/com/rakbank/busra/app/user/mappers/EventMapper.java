package com.rakbank.busra.app.user.mappers;

import com.rakbank.busra.app.user.dtos.EventDTO;
import com.rakbank.busra.app.user.models.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event eventCreateRequestDTOToEvent(EventDTO requestDTO);
    EventDTO eventToEventResponseDTO(Event requestDTO);

}
