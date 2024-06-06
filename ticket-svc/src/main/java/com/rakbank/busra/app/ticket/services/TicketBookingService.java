package com.rakbank.busra.app.ticket.services;

import com.rakbank.busra.app.ticket.common.exceptions.ApplicationException;
import com.rakbank.busra.app.ticket.dtos.TicketSaleRequestDTO;
import com.rakbank.busra.app.ticket.dtos.TicketSaleResponseDTO;
import com.rakbank.busra.app.ticket.mappers.TicketSaleMapper;
import com.rakbank.busra.app.ticket.models.TicketSale;
import com.rakbank.busra.app.ticket.models.TicketStatus;
import com.rakbank.busra.app.ticket.repositories.EventTicketInventoryRepository;
import com.rakbank.busra.app.ticket.repositories.EventTicketRepository;
import com.rakbank.busra.app.ticket.repositories.TicketSaleRepository;
import com.rakbank.busra.app.ticket.repositories.TicketTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.rakbank.busra.app.ticket.errors.ErrorCode.TICKET_EVENT_INVENTORY_NO_TICKET_TYPE;
import static com.rakbank.busra.app.ticket.errors.ErrorCode.TICKET_NOT_AVAILABLE;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TicketBookingService {
    private final TicketSaleRepository ticketSaleRepository;

    private final TicketTypeRepository ticketTypeRepository;
    private final EventTicketRepository eventTicketRepository;
    private final EventTicketInventoryRepository eventTicketInventoryRepository;
    private final TicketSaleMapper ticketSaleMapper;

    public TicketSaleResponseDTO book(TicketSaleRequestDTO bookingReq) {
        //step 1 - get the ticket type
        var ticketType = ticketTypeRepository.getByTicketTypeNameIgnoreCase(bookingReq.getTicketTypeName());

        //step 2 - get event inventory by eventId
        var eventTicketInventory = eventTicketInventoryRepository.getByEventId(bookingReq.getEventId());

        //step 3 - ensure the event sells the ticketType requested in booking
        var eventTicketRecord = eventTicketInventory.getEventTickets().stream()
                .filter(eventTicket -> eventTicket.getTicketType()
                        .getTicketTypeName().equalsIgnoreCase(bookingReq.getTicketTypeName()))
                .findFirst().orElseThrow(()-> new ApplicationException(
                        String.format("EventId %s does not support TicketType %s",
                                bookingReq.getEventId(), bookingReq.getTicketTypeName()),
                        TICKET_EVENT_INVENTORY_NO_TICKET_TYPE));

        //step 4 - make sure tickets are available for the ticketType in request
        int availableTickets = eventTicketRecord.getAvailableTickets();
        if(availableTickets <=0){
            throw new ApplicationException(
                    String.format("EventId %s does not have TicketType %s available to book",
                            bookingReq.getEventId(), bookingReq.getTicketTypeName()),
                    TICKET_NOT_AVAILABLE);
        }

        //step 4 - create ticket sale and save it
        var referenceId = UUID.randomUUID().toString();
        var ticketSale = ticketSaleMapper.toTicketSaleEntity(bookingReq);
        ticketSale.setTicketType(ticketType);

        //newly created ticket - next step is payment
        ticketSale.setTicketStatus(TicketStatus.CREATED);
        ticketSale.setReferenceId(referenceId);
        ticketSaleRepository.save(ticketSale);

        //step 5 - update values of ticketAvailable
        eventTicketRecord.setAvailableTickets(availableTickets-1);

        eventTicketRepository.saveAndFlush(eventTicketRecord);
        return ticketSaleMapper.toTicketSaleDTO(ticketSale);
    }

    public TicketSale cancel(String referenceId) {
        var ticket = ticketSaleRepository.getByReferenceIdIgnoreCase(referenceId);
        ticket.setTicketStatus(TicketStatus.CANCELLED);

        var eventTicketRecord = eventTicketRepository.getByEventTicketInventoryByEventId(ticket.getEventId());
        eventTicketRecord.setAvailableTickets(eventTicketRecord.getAvailableTickets()+1);

        ticketSaleRepository.saveAndFlush(ticket);
        eventTicketRepository.saveAndFlush(eventTicketRecord);
        return ticket;
    }

    public TicketSale fetchTicketByReferenceId(String referenceId) {
        return ticketSaleRepository.getByReferenceIdIgnoreCase(referenceId);
    }
}
