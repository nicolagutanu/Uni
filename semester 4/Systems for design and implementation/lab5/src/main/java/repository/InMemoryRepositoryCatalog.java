package repository;

import domain.BaseEntity;
import domain.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryRepositoryCatalog<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    private Map<ID, T> entities;

    public InMemoryRepositoryCatalog() { entities=new HashMap<>(); }

    /**
     *
     * @param id = catalog entry id
     * @return catalog entity with given id
     */
    @Override
    public Optional<T> findOne(ID id) {
        if (id==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.get(id));
    }

    /**
     *
     * @return all catalog entities
     */
    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities=entities.entrySet().stream()
                .map(entry->entry.getValue())
                .collect(Collectors.toSet());
        return allEntities;
    }

    /**
     *
     * @param entity = catalog entry
     * @return null if absent, existing mapping otherwise
     * @throws ValidatorException
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    /**
     *
     * @param id = catalog entry id
     * @return null if absent, existing entry otherwise
     */
    @Override
    public Optional<T> delete(ID id) {
        if (id==null) {
            throw new IllegalArgumentException("Id cannot be null\n");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    /**
     *
     * @param entity = catalog entry
     * @return null if absent, remapped value otherwise
     * @throws ValidatorException
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity==null) {
            throw new IllegalArgumentException("Entity cannot be null\n");
        }
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v)->entity));
    }
}
