package br.com.hrs.core.usecase;

import br.com.hrs.core.model.EntityKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public interface CrudUseCase<E extends EntityKey<ID>, ID> {

    Logger logger = LogManager.getLogger(CrudUseCase.class);

    E find(ID id);

    E save(E entity);

    void update(E entity);

    Collection<E> findAll();

    void delete(ID id);

    boolean exists(ID id);
}
