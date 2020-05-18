package br.com.hrs.core.repository;

import br.com.hrs.core.model.EntityKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Repository<E extends EntityKey<ID>, ID> {

    protected Logger logger = LogManager.getLogger(Repository.class);
    protected Map<ID, E> database = new HashMap<>();
    
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
