package repository.db;

import domain.Ticket;
import domain.validators.ValidatorException;
import repository.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBRepositoryTicket implements Repository<Integer, Ticket> {
    private String url;
    private String user;
    private String password;

    public DBRepositoryTicket(String u, String us, String p) {
        url=u;
        user=us;
        password=p;
    }

    @Override
    public Optional<Ticket> findOne(Integer id) {
        String sql="select idMovie, idCinema, price from Ticket where id="+id.toString() ;
        Ticket ticket=new Ticket();
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            if (rs.next()) {
                Integer idMovie=rs.getInt("idMovie");
                Integer idCinema=rs.getInt("idCinema");
                Float price=rs.getFloat("price");
                ticket=new Ticket(idMovie, idCinema, price);
                ticket.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(ticket);
    }

    @Override
    public Iterable<Ticket> findAll() {
        List<Ticket> tickets=new ArrayList<>();
        String sql="select * from Ticket";
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            while (rs.next()) {
                Integer id=rs.getInt("id");
                Integer idMovie=rs.getInt("idMovie");
                Integer idCinema=rs.getInt("idCinema");
                Float price=rs.getFloat("price");
                Ticket ticket=new Ticket(idMovie, idCinema, price);
                ticket.setId(id);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tickets;
    }

    @Override
    public Optional<Ticket> save(Ticket entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="insert into Ticket values (?, ?, ?, ?)";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getIdMovie());
            ps.setInt(3, entity.getIdCinema());
            ps.setFloat(4, entity.getPrice());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Ticket> delete(Integer id) {
        String sql="delete from Ticket where id=?";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Ticket> update(Ticket entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="update Ticket set idMovie=?, idCinema=?, price=? where id=?";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdMovie());
            ps.setInt(2, entity.getIdCinema());
            ps.setFloat(3, entity.getPrice());
            ps.setInt(4, entity.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
