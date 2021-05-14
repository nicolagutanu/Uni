package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();

    Ticket saveTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    void deleteTicket(Integer id);
}
