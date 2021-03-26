package repository.file;

import domain.Catalog;
import domain.validators.ValidatorException;
import repository.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepositoryCatalog implements Repository<Integer, Catalog> {

    private Map<Integer, Catalog> entities;

    public FileRepositoryCatalog(){
        loadData();
    }

    public void loadData(){
        entities = new HashMap<>();
        try {
            File myObj = new File("D:\\School\\sem4\\sdi\\lab2x-les-aristocats\\lab4\\Catalog.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] stringsRead = data.split(";");
                Integer id = Integer.valueOf(Arrays.asList(stringsRead).get(0));
                Integer idMovie = Integer.valueOf(Arrays.asList(stringsRead).get(1));
                Integer idGenre = Integer.valueOf(Arrays.asList(stringsRead).get(2));
                Catalog cat = new Catalog(idMovie, idGenre);
                cat.setId(id);
                entities.put(id,cat);
                System.out.println("aici");
                System.out.println(id + " " + idGenre + " " + idMovie);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Catalog> findOne(Integer idToFind) {
        if (idToFind==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.get(idToFind));
    }

    @Override
    public Iterable<Catalog> findAll() {
        Set<Catalog> allEntities=entities.entrySet().stream()
                .map(entry->entry.getValue())
                .collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<Catalog> save(Catalog entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<Catalog> delete(Integer id) {
        if (id==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<Catalog> update(Catalog entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v)->entity));
    }
}
