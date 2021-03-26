package repository.db;

import domain.Movie;
import domain.validators.ValidatorException;
import repository.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBRepositoryMovie implements Repository<Integer, Movie> {
    private String url;
    private String user;
    private String password;

    public DBRepositoryMovie(String u, String us, String p) {
        url=u;
        user=us;
        password=p;
    }

    @Override
    public Optional<Movie> findOne(Integer id) {
        String sql="select name, rating from Movie where id="+id.toString() ;
        Movie movie=new Movie();
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("name");
                Float rating = rs.getFloat("rating");
                movie = new Movie(name, rating);
                movie.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(movie);
    }

    @Override
    public Iterable<Movie> findAll() {
        List<Movie> movies=new ArrayList<>();
        String sql="select * from Movie";
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            while (rs.next()) {
                Integer id=rs.getInt("id");
                String name=rs.getString("name");
                Float rating=rs.getFloat("rating");
                Movie movie=new Movie(name, rating);
                movie.setId(id);
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movies;
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="insert into Movie values (?, ?, ?)";
        try (var connection=DriverManager.getConnection(url, user, password);
        var ps=connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setFloat(3, entity.getRating());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Movie> delete(Integer id) {
        String sql="delete from Movie where id=?";
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
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="update Movie set name=?, rating=? where id=?";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setFloat(2, entity.getRating());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
