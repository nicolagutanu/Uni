package repository;

import domain.Catalog;
import domain.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRepositoryCatalog {
    @Test
    public void testFindOne() throws Exception {
        Catalog catalog1=new Catalog(1,1);
        catalog1.setId(1);
        Catalog catalog2=new Catalog(2, 2);
        catalog2.setId(2);
        InMemoryRepositoryCatalog<Integer, Catalog> repositoryCatalog=new InMemoryRepositoryCatalog<>();
        repositoryCatalog.save(catalog1);
        repositoryCatalog.save(catalog2);
        assert(repositoryCatalog.findOne(2).get().getId().equals(2));
    }

    @Test
    public void testFindAll() throws Exception {
        Catalog catalog1=new Catalog(1,1);
        catalog1.setId(1);
        Catalog catalog2=new Catalog(2, 2);
        catalog2.setId(2);
        InMemoryRepositoryCatalog<Integer, Catalog> repositoryCatalog=new InMemoryRepositoryCatalog<>();
        repositoryCatalog.save(catalog1);
        repositoryCatalog.save(catalog2);
        assert(repositoryCatalog.findAll().spliterator().getExactSizeIfKnown()==2);
    }

    @Test
    public void testSave() throws Exception {
        Catalog catalog1=new Catalog(1,1);
        catalog1.setId(1);
        InMemoryRepositoryCatalog<Integer, Catalog> repositoryCatalog=new InMemoryRepositoryCatalog<>();
        repositoryCatalog.save(catalog1);
        assert(repositoryCatalog.findOne(1).get().getId().equals(1));
    }

    @Test
    public void testSaveException() throws Exception {
        Catalog catalog2=null;
        InMemoryRepositoryCatalog<Integer, Catalog> repositoryCatalog=new InMemoryRepositoryCatalog<>();
        assertThrows(IllegalArgumentException.class, ()->repositoryCatalog.save(catalog2));
    }

    @Test
    public void testDelete() throws Exception {
        Catalog catalog1=new Catalog(1,1);
        catalog1.setId(1);
        InMemoryRepositoryCatalog<Integer, Catalog> repositoryCatalog=new InMemoryRepositoryCatalog<>();
        repositoryCatalog.save(catalog1);
        repositoryCatalog.delete(1);
        assert(repositoryCatalog.findAll().spliterator().getExactSizeIfKnown()==0);
    }

    @Test
    public void testUpdate() throws Exception {
        Catalog catalog1=new Catalog(1,1);
        catalog1.setId(1);
        InMemoryRepositoryCatalog<Integer, Catalog> repositoryCatalog=new InMemoryRepositoryCatalog<>();
        repositoryCatalog.save(catalog1);
        Catalog catalog2=new Catalog(2, 2);
        catalog2.setId(1);
        repositoryCatalog.update(catalog2);
        assert(repositoryCatalog.findOne(1).get().getIdMovie().equals(2));
    }

    @Test
    public void testUpdateException() throws Exception {
        Catalog catalog2=null;
        InMemoryRepositoryCatalog<Integer, Catalog> repositoryCatalog=new InMemoryRepositoryCatalog<>();
        assertThrows(IllegalArgumentException.class, ()->repositoryCatalog.save(catalog2));
    }
}
