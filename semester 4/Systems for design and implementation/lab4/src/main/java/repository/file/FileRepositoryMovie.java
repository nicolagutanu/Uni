package repository.file;

import domain.Movie;
import domain.validators.ValidatorException;
import repository.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepositoryMovie implements Repository<Integer, Movie> {
    private Map<Integer, Movie> entities;

    public FileRepositoryMovie(){
        loadData();
    }

    public void loadData(){
        entities = new HashMap<>();
        try {
            File myObj = new File("D:\\School\\sem4\\sdi\\lab2x-les-aristocats\\lab4\\Movie.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] stringsRead = data.split(";");
                Integer id = Integer.valueOf(Arrays.asList(stringsRead).get(0));
                String name = Arrays.asList(stringsRead).get(1);
                Float rating = Float.valueOf(Integer.valueOf(Arrays.asList(stringsRead).get(2)));
                Movie cat = new Movie(name, rating);
                cat.setId(id);
                entities.put(id,cat);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> findOne(Integer idToFind) {
        if (idToFind==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.get(idToFind));
    }

    @Override
    public Iterable<Movie> findAll() {
        Set<Movie> allEntities=entities.entrySet().stream()
                .map(entry->entry.getValue())
                .collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<Movie> delete(Integer id) {
        if (id==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v)->entity));
    }
}
