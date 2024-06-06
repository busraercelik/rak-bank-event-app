package com.rakbank.busra.app.ticket;

import com.rakbank.busra.app.ticket.models.Currency;
import com.rakbank.busra.app.ticket.models.TicketType;
import com.rakbank.busra.app.ticket.repositories.TicketTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TicketTypeRepository ticketTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        var vipTicket = new TicketType();
        vipTicket.setAmount(new BigDecimal(100));
        vipTicket.setCurrency(Currency.AED);
        vipTicket.setTicketTypeName("VIP");

        ticketTypeRepository.save(vipTicket);

        var regularTicketType = new TicketType();
        regularTicketType.setAmount(new BigDecimal(100));
        regularTicketType.setCurrency(Currency.AED);
        regularTicketType.setTicketTypeName("REGULAR");

        ticketTypeRepository.save(regularTicketType);
    }
}
