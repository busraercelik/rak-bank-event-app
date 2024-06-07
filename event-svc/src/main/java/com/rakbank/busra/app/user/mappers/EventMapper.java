package com.rakbank.busra.app.user.mappers;

import com.rakbank.busra.app.user.dtos.EventDTO;
import com.rakbank.busra.app.user.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "dateTo", ignore = true)
    @Mapping(target = "dateFrom", ignore = true)
    Event eventDTOToEvent(EventDTO requestDTO);
    EventDTO eventToEventDTO(Event requestDTO);

}
