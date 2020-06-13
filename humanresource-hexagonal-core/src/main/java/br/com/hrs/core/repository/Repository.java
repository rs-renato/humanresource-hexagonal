package br.com.hrs.core.repository;

import br.com.hrs.core.model.EntityKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public interface Repository<E extends EntityKey<ID>, ID> {

    Logger logger = LogManager.getLogger(Repository.class);
 
    Optional<E> findById(ID id);
    
    E save(E entity);
    
    void update(E entity);
    
    List<E> findAll();

    void deleteById(ID id);
    
    boolean existsById(ID id);
}
