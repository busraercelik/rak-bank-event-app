package com.rakbank.busra.app.eventmgmt.mappers;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.requests.EventTicketInventoryRequestDTO;
import com.rakbank.busra.app.eventmgmt.dtos.requests.EventCreateBusinessRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessRequestMapper {
    EventDTO toEventDTO(EventCreateBusinessRequest src);

    @Mapping(source = "eventId", target = "eventId")
    @Mapping(source = "src.ticketBookingCapacities", target = "ticketBookingCapacities")
    EventTicketInventoryRequestDTO toEventTicketInventoryRequestDTO(Long eventId, EventCreateBusinessRequest src);
}
