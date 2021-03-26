package repository.db;

import domain.Cinema;
import domain.validators.ValidatorException;
import repository.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBRepositoryCinema implements Repository<Integer, Cinema> {
    private String url;
    private String user;
    private String password;

    public DBRepositoryCinema(String u, String us, String p) {
        url=u;
        user=us;
        password=p;
    }

    @Override
    public Optional<Cinema> findOne(Integer id) {
        String sql="select name, nrOfSeats from Cinema where id="+id.toString() ;
        Cinema cinema=new Cinema();
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("name");
                Integer nrOfSeats = rs.getInt("nrOfSeats");
                cinema = new Cinema(name, nrOfSeats);
                cinema.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(cinema);
    }

    @Override
    public Iterable<Cinema> findAll() {
        List<Cinema> cinemas=new ArrayList<>();
        String sql="select * from Cinema";
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            while (rs.next()) {
                Integer id=rs.getInt("id");
                String name=rs.getString("name");
                Integer nrOfSeats=rs.getInt("nrOfSeats");
                Cinema cinema=new Cinema(name, nrOfSeats);
                cinema.setId(id);
                cinemas.add(cinema);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cinemas;
    }

    @Override
    public Optional<Cinema> save(Cinema entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="insert into Cinema values (?, ?, ?)";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setInt(3, entity.getNrOfSeats());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Cinema> delete(Integer id) {
        String sql="delete from Cinema where id=?";
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
    public Optional<Cinema> update(Cinema entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="update Cinema set name=?, nrOfSeats=? where id=?";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getNrOfSeats());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
