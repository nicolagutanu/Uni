package repository.file;

import domain.Genre;
import domain.validators.ValidatorException;
import repository.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepositoryGenre implements Repository<Integer, Genre> {
    private Map<Integer, Genre> entities;

    public FileRepositoryGenre(){
        loadData();
    }

    public void loadData(){
        entities = new HashMap<>();
        try {
            File myObj = new File("D:\\School\\sem4\\sdi\\lab2x-les-aristocats\\lab4\\Genre.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] stringsRead = data.split(";");
                Integer id = Integer.valueOf(Arrays.asList(stringsRead).get(0));
                String name = Arrays.asList(stringsRead).get(1);
                Genre genre = new Genre(name);
                genre.setId(id);
                entities.put(id,genre);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Genre> findOne(Integer idToFind) {
        if (idToFind==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.get(idToFind));
    }

    @Override
    public Iterable<Genre> findAll() {
        Set<Genre> allEntities=entities.entrySet().stream()
                .map(entry->entry.getValue())
                .collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<Genre> save(Genre entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<Genre> delete(Integer id) {
        if (id==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<Genre> update(Genre entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v)->entity));
    }
}
