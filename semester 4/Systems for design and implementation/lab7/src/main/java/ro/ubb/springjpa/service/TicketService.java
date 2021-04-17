package ro.ubb.springjpa.service;

import ro.ubb.springjpa.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();

    void saveTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void deleteTicketById(Integer id);
}
