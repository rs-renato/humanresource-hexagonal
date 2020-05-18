package br.com.hrs.core.repository;

import br.com.hrs.core.model.EntityKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

 public interface Repository<E extends EntityKey<ID>, ID> {

    Logger logger = LogManager.getLogger(Repository.class);
 
    E find(ID id);
    
    ID save(E entity);
    
    boolean update(E entity);
    
    Collection<E> findAll();

    boolean delete(ID id);
    
    boolean exists(ID id);
}
