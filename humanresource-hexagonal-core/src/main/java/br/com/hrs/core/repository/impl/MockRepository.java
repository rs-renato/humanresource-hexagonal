package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class MockRepository <E extends EntityKey<ID>, ID> implements Repository<E, ID>{

    protected Map<ID, E> database = new HashMap<>();

    public MockRepository() {
        loadMockDatabase();
    }

    abstract void loadMockDatabase();

    public E find(ID id) {
        logger.info("Fake database -> find({}})", id);
        return this.database.get(id);
    }

    public ID save(E entity) {
        logger.info("Fake database ->  save({}})", entity);
        this.database.put(entity.getId(), entity);
        return entity.getId();
    }

    public boolean update(E entity) {
        logger.info("Fake database ->  update({}})", entity);
        return this.database.put(entity.getId(), entity) != null;
    }

    public Collection<E> findAll() {
        logger.info("Fake database ->  Collection<Country> findAll()");
        return this.database.values();
    }

    public boolean delete(ID id) {
        logger.info("Fake database ->  delete({}})", id);
        return this.database.remove(id) != null;
    }

    public boolean exists(ID id) {
        logger.info("Fake database ->  exists({}})", id);
        return this.database.containsKey(id);
    }
}
