package com.rakbank.busra.app.eventmgmt.mappers;

import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.CreateTicketEventInventoryResultDTO;
import com.rakbank.busra.app.eventmgmt.dtos.responses.EventCreateBusinessResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessResponseMapper {

    @Mapping(source = "inventoryDTO.inventory.eventTickets", target = "eventTickets")
    EventCreateBusinessResponse toEventCreateBusinessResponse(EventDTO eventDTO, CreateTicketEventInventoryResultDTO inventoryDTO);
}
