package repository.file;

import domain.Cinema;
import domain.validators.ValidatorException;
import repository.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepositoryCinema implements Repository<Integer, Cinema> {
    private Map<Integer, Cinema> entities;

    public FileRepositoryCinema(){
        loadData();
    }

    public void loadData(){
        entities = new HashMap<>();
        try {
            File myObj = new File("D:\\School\\sem4\\sdi\\lab2x-les-aristocats\\lab4\\Cinema.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] stringsRead = data.split(";");
                Integer id = Integer.valueOf(Arrays.asList(stringsRead).get(0));
                String name = Arrays.asList(stringsRead).get(1);
                Integer nrOfSeats = Integer.valueOf(Arrays.asList(stringsRead).get(2));
                Cinema cinema = new Cinema(name, nrOfSeats);
                cinema.setId(id);
                entities.put(id,cinema);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Cinema> findOne(Integer idToFind) {
        if (idToFind==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.get(idToFind));
    }

    @Override
    public Iterable<Cinema> findAll() {
        Set<Cinema> allEntities=entities.entrySet().stream()
                .map(entry->entry.getValue())
                .collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<Cinema> save(Cinema entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<Cinema> delete(Integer id) {
        if (id==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<Cinema> update(Cinema entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v)->entity));
    }
}
