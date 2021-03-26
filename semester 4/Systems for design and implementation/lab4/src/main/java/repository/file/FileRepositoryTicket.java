package repository.file;

import domain.Ticket;
import domain.validators.ValidatorException;
import repository.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepositoryTicket implements Repository<Integer, Ticket> {
    private Map<Integer, Ticket> entities;

    public FileRepositoryTicket(){
        loadData();
    }

    public void loadData(){
        entities = new HashMap<>();
        try {
            File myObj = new File("D:\\School\\sem4\\sdi\\lab2x-les-aristocats\\lab4\\Ticket.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] stringsRead = data.split(";");
                Integer id = Integer.valueOf(Arrays.asList(stringsRead).get(0));
                Integer idMovie = Integer.valueOf(Arrays.asList(stringsRead).get(1));
                Integer idCinema = Integer.valueOf(Arrays.asList(stringsRead).get(2));
                Float price = (float) Integer.parseInt(Arrays.asList(stringsRead).get(3));
                Ticket cat = new Ticket(idMovie, idCinema, price);
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
    public Optional<Ticket> findOne(Integer idToFind) {
        if (idToFind==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.get(idToFind));
    }

    @Override
    public Iterable<Ticket> findAll() {
        Set<Ticket> allEntities=entities.entrySet().stream()
                .map(entry->entry.getValue())
                .collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<Ticket> save(Ticket entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<Ticket> delete(Integer id) {
        if (id==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<Ticket> update(Ticket entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v)->entity));
    }
}
