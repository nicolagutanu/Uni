package repository;

import domain.Cinema;
import domain.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRepositoryCinema {
    @Test
    public void testFindOne() throws Exception {
        Cinema cinema1=new Cinema("a", 1);
        cinema1.setId(1);
        Cinema cinema2=new Cinema("b", 2);
        cinema2.setId(2);
        InMemoryRepositoryCinema<Integer, Cinema> repositoryCinema=new InMemoryRepositoryCinema<>();
        repositoryCinema.save(cinema1);
        repositoryCinema.save(cinema2);
        assert(repositoryCinema.findOne(2).get().getId().equals(2));
    }

    @Test
    public void testFindAll() throws Exception {
        Cinema cinema1=new Cinema("a", 1);
        cinema1.setId(1);
        Cinema cinema2=new Cinema("b", 2);
        cinema2.setId(2);
        InMemoryRepositoryCinema<Integer, Cinema> repositoryCinema=new InMemoryRepositoryCinema<>();
        repositoryCinema.save(cinema1);
        repositoryCinema.save(cinema2);
        assert(repositoryCinema.findAll().spliterator().getExactSizeIfKnown()==2);
    }

    @Test
    public void testSave() throws Exception {
        Cinema cinema1=new Cinema("a", 1);
        cinema1.setId(1);
        InMemoryRepositoryCinema<Integer, Cinema> repositoryCinema=new InMemoryRepositoryCinema<>();
        repositoryCinema.save(cinema1);
        assert(repositoryCinema.findOne(1).get().getId().equals(1));
    }

    @Test
    public void testSaveException() throws Exception {
        Cinema cinema1=null;
        InMemoryRepositoryCinema<Integer, Cinema> repositoryCinema=new InMemoryRepositoryCinema<>();
        assertThrows(IllegalArgumentException.class, ()->repositoryCinema.save(cinema1));
    }

    @Test
    public void testDelete() throws Exception {
        Cinema cinema1=new Cinema("a", 1);
        cinema1.setId(1);
        InMemoryRepositoryCinema<Integer, Cinema> repositoryCinema=new InMemoryRepositoryCinema<>();
        repositoryCinema.save(cinema1);
        repositoryCinema.delete(1);
        assert(repositoryCinema.findAll().spliterator().getExactSizeIfKnown()==0);
    }

    @Test
    public void testUpdate() throws Exception {
        Cinema cinema1=new Cinema("a", 1);
        cinema1.setId(1);
        InMemoryRepositoryCinema<Integer, Cinema> repositoryCinema=new InMemoryRepositoryCinema<>();
        repositoryCinema.save(cinema1);
        repositoryCinema.save(cinema1);
        Cinema cinema2=new Cinema("b", 2);
        cinema2.setId(1);
        repositoryCinema.update(cinema2);
        assert(repositoryCinema.findOne(1).get().getName().equals("b"));
    }

    @Test
    public void testUpdateException() throws Exception {
        Cinema cinema1=null;
        InMemoryRepositoryCinema<Integer, Cinema> repositoryCinema=new InMemoryRepositoryCinema<>();
        assertThrows(IllegalArgumentException.class, ()->repositoryCinema.update(cinema1));
    }
}
