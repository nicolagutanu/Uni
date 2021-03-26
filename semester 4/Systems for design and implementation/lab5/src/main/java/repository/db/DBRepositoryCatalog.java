package repository.db;

import domain.Catalog;
import domain.validators.ValidatorException;
import repository.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBRepositoryCatalog implements Repository<Integer, Catalog> {
    private String url;
    private String user;
    private String password;

    public DBRepositoryCatalog(String u, String us, String p) {
        url=u;
        user=us;
        password=p;
    }

    @Override
    public Optional<Catalog> findOne(Integer id) {
        String sql="select idMovie, idGenre from Catalog where id="+id.toString() ;
        Catalog catalog=new Catalog();
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            if (rs.next()) {
                Integer idMovie=rs.getInt("idMovie");
                Integer idGenre=rs.getInt("idGenre");
                catalog=new Catalog(idMovie, idGenre);
                catalog.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(catalog);
    }

    @Override
    public Iterable<Catalog> findAll() {
        List<Catalog> catalogs=new ArrayList<>();
        String sql="select * from Catalog";
        try (var connection= DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql);
             var rs=ps.executeQuery()) {
            while (rs.next()) {
                Integer id=rs.getInt("id");
                Integer idMovie=rs.getInt("idMovie");
                Integer idGenre=rs.getInt("idGenre");
                Catalog catalog=new Catalog(idMovie, idGenre);
                catalog.setId(id);
                catalogs.add(catalog);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return catalogs;
    }

    @Override
    public Optional<Catalog> save(Catalog entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="insert into Catalog values (?, ?, ?)";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getIdMovie());
            ps.setInt(3, entity.getIdGenre());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Catalog> delete(Integer id) {
        String sql="delete from Catalog where id=?";
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
    public Optional<Catalog> update(Catalog entity) throws ValidatorException {
        if (entity==null)
            throw new IllegalArgumentException("Entity cannot be null\n");
        String sql="update Catalog set idMovie=?, idGenre=? where id=?";
        try (var connection=DriverManager.getConnection(url, user, password);
             var ps=connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdMovie());
            ps.setInt(2, entity.getIdGenre());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
