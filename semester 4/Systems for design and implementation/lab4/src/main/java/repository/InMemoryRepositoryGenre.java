package repository;

import domain.BaseEntity;
import domain.Genre;
import domain.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryRepositoryGenre<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    Map<ID,T> genres;

    public InMemoryRepositoryGenre(){this.genres = new HashMap<>(); }

    /**
     *
     * @param id = genre id
     * @return genre with given id
     */
    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null\n");
        }
        return Optional.ofNullable(genres.get(id));
    }

    /**
     *
     * @return all genre entities
     */
    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = genres.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    /**
     *
     * @param entity = genre
     * @return null if absent, existing mapping otherwise
     * @throws ValidatorException
     */
    @Override
    public Optional<T> save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Id must not be null\n");
        }
        return Optional.ofNullable(genres.putIfAbsent(entity.getId(), entity));
    }

    /**
     *
     * @param id = genre id
     * @return null if absent, existing entry otherwise
     */
    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null\n");
        }
        return Optional.ofNullable(genres.remove(id));
    }

    /**
     *
     * @param entity = genre
     * @return null if absent, remapped value otherwise
     * @throws ValidatorException
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null\n");
        }
        return Optional.ofNullable(genres.computeIfPresent(entity.getId(), (k, v) -> entity));
    }

}
