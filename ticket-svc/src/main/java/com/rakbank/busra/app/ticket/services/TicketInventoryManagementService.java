package com.rakbank.busra.app.ticket.services;

import com.rakbank.busra.app.ticket.common.exceptions.ApplicationException;
import com.rakbank.busra.app.ticket.dtos.CreateTicketEventInventoryResultDTO;
import com.rakbank.busra.app.ticket.dtos.EventTicketInventoryDTO;
import com.rakbank.busra.app.ticket.errors.ErrorCode;
import com.rakbank.busra.app.ticket.models.EventTicket;
import com.rakbank.busra.app.ticket.models.EventTicketInventory;
import com.rakbank.busra.app.ticket.models.TicketType;
import com.rakbank.busra.app.ticket.repositories.EventTicketInventoryRepository;
import com.rakbank.busra.app.ticket.repositories.EventTicketRepository;
import com.rakbank.busra.app.ticket.repositories.TicketTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TicketInventoryManagementService {

    private final TicketTypeRepository ticketTypeRepository;
    private final EventTicketRepository eventTicketRepository;
    private final EventTicketInventoryRepository eventTicketInventoryRepository;

    public CreateTicketEventInventoryResultDTO createTicketEventInventory(EventTicketInventoryDTO dto) {
        var existingInventory = eventTicketInventoryRepository.findByEventId(dto.getEventId());

        if(Objects.nonNull(existingInventory))
            return new CreateTicketEventInventoryResultDTO(false, existingInventory);

        var eventTicketInventory = new EventTicketInventory();
        eventTicketInventory.setEventId(dto.getEventId());

        eventTicketInventoryRepository.save(eventTicketInventory);

        var eventTickets = dto.getTicketBookingCapacities().stream()
                .map(ticketBookingCapacity -> createEventTicket(eventTicketInventory, ticketBookingCapacity))
                .toList();

        eventTicketInventory.getEventTickets().addAll(eventTickets);

        eventTicketInventoryRepository.saveAndFlush(eventTicketInventory);
        return new CreateTicketEventInventoryResultDTO(true, eventTicketInventory);
    }

    public EventTicketInventory getEventTicketInventoryByEventId(Long eventId){
        return eventTicketInventoryRepository.findByEventId(eventId);
    }

    private EventTicket createEventTicket(
            EventTicketInventory eventTicketInventory,
            EventTicketInventoryDTO.TicketBookingCapacity ticketBookingCapacity) {
        var ticketTypeName = ticketBookingCapacity.getTicketTypeName();
        var ticketType = ticketTypeRepository.findByTicketTypeNameIgnoreCase(ticketTypeName)
                .orElseThrow(()-> new ApplicationException(
                        String.format("Ticket Type with name : %s not found", ticketTypeName),
                        ErrorCode.TICKET_TYPE_NOT_FOUND));

        var eventTicket = new EventTicket();
        eventTicket.setAvailableTickets(ticketBookingCapacity.getTotalTickets());
        eventTicket.setTotalTickets(ticketBookingCapacity.getTotalTickets());
        eventTicket.setTicketType(ticketType);
        eventTicket.setEventTicketInventory(eventTicketInventory);
        eventTicketRepository.saveAndFlush(eventTicket);
        return eventTicket;
    }

    public List<TicketType> getAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }
}
