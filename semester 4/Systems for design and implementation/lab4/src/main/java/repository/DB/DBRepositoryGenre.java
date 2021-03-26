package repository.db;

import domain.Genre;
import domain.validators.ValidatorException;
import repository.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBRepositoryGenre implements Repository<Integer, Genre> {
    private String url;
    private String user;
    private String password;

    public DBRepositoryGenre(String u, String us, String p) {
        url=u;
        user=us;
        password=p;
    }

    @Override
    public Optional<Genre> findOne(Integer id) {
        String sql="select name from Genre where id="+id.toString() ;
        Genre genre=new Genre();
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("name");
                genre = new Genre(name);
                genre.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(genre);
    }

    @Override
    public Iterable<Genre> findAll() {
        List<Genre> genres=new ArrayList<>();
        String sql="select * from Genre";
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            while (rs.next()) {
                Integer id=rs.getInt("id");
                String name=rs.getString("name");
                Genre genre=new Genre(name);
                genre.setId(id);
                genres.add(genre);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genres;
    }

    @Override
    public Optional<Genre> save(Genre entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="insert into Genre values (?, ?)";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Genre> delete(Integer id) {
        String sql="delete from Genre where id=?";
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
    public Optional<Genre> update(Genre entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="update Genre set name=? where id=?";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
