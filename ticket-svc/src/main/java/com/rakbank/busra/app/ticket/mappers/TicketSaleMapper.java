package com.rakbank.busra.app.ticket.mappers;

import com.rakbank.busra.app.ticket.dtos.TicketSaleRequestDTO;
import com.rakbank.busra.app.ticket.dtos.TicketSaleResponseDTO;
import com.rakbank.busra.app.ticket.models.TicketSale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketSaleMapper {
    TicketSale toTicketSaleEntity(TicketSaleRequestDTO bookingReq);

    @Mapping(source = "ticketType.ticketTypeName", target = "ticketTypeName")
    TicketSaleResponseDTO toTicketSaleDTO(TicketSale ticketSale);
}
