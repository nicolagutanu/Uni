package repository;

import domain.Movie;
import domain.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRepositoryTicket {
    @Test
    public void testFindOne() throws Exception {
        Ticket ticket1=new Ticket(1, 1, 1.0f);
        ticket1.setId(1);
        Ticket ticket2=new Ticket(2,2,2.0f);
        ticket2.setId(2);
        InMemoryRepositoryTicket<Integer, Ticket> repositoryTicket=new InMemoryRepositoryTicket<>();
        repositoryTicket.save(ticket1);
        repositoryTicket.save(ticket2);
        assert(repositoryTicket.findOne(2).get().getId().equals(2));
    }

    @Test
    public void testFindAll() throws Exception {
        Ticket ticket1=new Ticket(1, 1, 1.0f);
        ticket1.setId(1);
        Ticket ticket2=new Ticket(2,2,2.0f);
        ticket2.setId(2);
        InMemoryRepositoryTicket<Integer, Ticket> repositoryTicket=new InMemoryRepositoryTicket<>();
        repositoryTicket.save(ticket1);
        repositoryTicket.save(ticket2);
        assert(repositoryTicket.findAll().spliterator().getExactSizeIfKnown()==2);
    }

    @Test
    public void testSave() throws Exception {
        Ticket ticket1=new Ticket(1, 1, 1.0f);
        ticket1.setId(1);
        InMemoryRepositoryTicket<Integer, Ticket> repositoryTicket=new InMemoryRepositoryTicket<>();
        repositoryTicket.save(ticket1);
        assert(repositoryTicket.findOne(1).get().getId().equals(1));
    }

    @Test
    public void testSaveException() throws Exception {
        Ticket ticket1=null;
        InMemoryRepositoryTicket<Integer, Ticket> repositoryTicket=new InMemoryRepositoryTicket<>();
        assertThrows(IllegalArgumentException.class, ()->repositoryTicket.save(ticket1));
    }

    @Test
    public void testDelete() throws Exception {
        Ticket ticket1=new Ticket(1, 1, 1.0f);
        ticket1.setId(1);
        InMemoryRepositoryTicket<Integer, Ticket> repositoryTicket=new InMemoryRepositoryTicket<>();
        repositoryTicket.save(ticket1);
        repositoryTicket.delete(1);
        assert(repositoryTicket.findAll().spliterator().getExactSizeIfKnown()==0);
    }

    @Test
    public void testUpdate() throws Exception {
        Ticket ticket1=new Ticket(1, 1, 1.0f);
        ticket1.setId(1);
        InMemoryRepositoryTicket<Integer, Ticket> repositoryTicket=new InMemoryRepositoryTicket<>();
        repositoryTicket.save(ticket1);
        Ticket ticket2=new Ticket(2,2,2.0f);
        ticket2.setId(1);
        repositoryTicket.update(ticket2);
        assert(repositoryTicket.findOne(1).get().getIdMovie().equals(2));
    }

    @Test
    public void testUpdateException() throws Exception {
        Ticket ticket1=null;
        InMemoryRepositoryTicket<Integer, Ticket> repositoryTicket=new InMemoryRepositoryTicket<>();
        assertThrows(IllegalArgumentException.class, ()->repositoryTicket.update(ticket1));
    }
}
